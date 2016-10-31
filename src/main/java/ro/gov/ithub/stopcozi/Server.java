/*
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package ro.gov.ithub.stopcozi;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.inflector.SwaggerInflector;
import io.swagger.inflector.config.Configuration;
import io.swagger.inflector.config.ControllerFactory;
import io.swagger.inflector.processors.JsonNodeExampleSerializer;
import io.swagger.inflector.processors.XMLExampleProvider;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.LoggerFactory;
import ro.gov.ithub.stopcozi.model.repo.Agency;
import ro.gov.ithub.stopcozi.model.repo.Appointment;
import ro.gov.ithub.stopcozi.model.repo.Desk;
import ro.gov.ithub.stopcozi.model.repo.Office;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class Server extends Application<ServerConfiguration> {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Server.class);
    private final HibernateBundle<ServerConfiguration> hibernate = new HibernateBundle<ServerConfiguration>(
            Agency.class, Appointment.class, Desk.class, Office.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
            return configuration.getDatabase();
        }
    };
    private GuiceBundle<ServerConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new Server().run(args);
    }

    @Override
    public String getName() {
        return "StopCozi API";
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<ServerConfiguration>newBuilder()
            .addModule(new GuiceModule(hibernate))
            .setConfigClass(ServerConfiguration.class)
            .build(Stage.DEVELOPMENT);

        bootstrap.addBundle(hibernate);

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("crossOriginRequsts", CrossOriginFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        Configuration config = Configuration.read(configuration.getConfig());
        config.setControllerFactory(new GuiceControllerFactory(guiceBundle.getInjector()));
        SwaggerInflector inflector = new SwaggerInflector(config);
        environment.jersey().getResourceConfig().registerResources(inflector.getResources());
        
        // add serializers for swagger
        environment.jersey().register(SwaggerSerializers.class);

        // example serializers
        environment.jersey().register(XMLExampleProvider.class);

        // mappers
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new JsonNodeExampleSerializer());
        Json.mapper().registerModule(simpleModule);
        Yaml.mapper().registerModule(simpleModule);
    }

    private class GuiceControllerFactory implements ControllerFactory {
        private final Injector injector;

        public GuiceControllerFactory(Injector injector) {
            this.injector = injector;
        }

        public Object instantiateController(Class cls) throws IllegalAccessException, InstantiationException {
            return this.injector.getInstance(cls);
        }
    }
}
