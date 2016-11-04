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

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.hibernate.SessionFactory;
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

    private final HibernateBundle<ServerConfiguration> hibernateBundle = new HibernateBundle<ServerConfiguration>(
        Agency.class, Appointment.class, Desk.class, Office.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(ServerConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    public static void main(String[] args) throws Exception {
        new Server().run(args);
    }

    @Override
    public String getName() {
        return "StopCozi API";
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);

        bootstrap.addBundle(GuiceBundle.<ServerConfiguration>newBuilder()
            .addModule(new AbstractModule(){
                @Override protected void configure() {}
                @Provides SessionFactory sessionFactoryProvider() { return hibernateBundle.getSessionFactory();}
            })
            .setConfigClass(ServerConfiguration.class)
            .enableAutoConfig(getClass().getPackage().getName())
            .build(Stage.DEVELOPMENT)
        );

        bootstrap.addBundle(new Java8Bundle());
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("crossOriginRequsts", CrossOriginFilter.class);
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new ApiListingResource());
    }
}
