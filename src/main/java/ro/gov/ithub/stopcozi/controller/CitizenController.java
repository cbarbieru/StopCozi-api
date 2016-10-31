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

package ro.gov.ithub.stopcozi.controller;

import io.swagger.inflector.models.RequestContext;
import io.swagger.inflector.models.ResponseContext;
import ro.gov.ithub.stopcozi.dao.AgencyDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response.Status;
import java.util.Collections;

@Singleton
public class CitizenController {

    private final AgencyDao agencyDao;

    @Inject
    public CitizenController(AgencyDao agencyDao){
        this.agencyDao = agencyDao;
    }

    public ResponseContext listAgencies(RequestContext request, String countyCode) {
        return new ResponseContext()
            .status(Status.OK)
            .entity(Collections.emptyList());
    }

    public ResponseContext listOffices(RequestContext request, String agencyId) {
        return new ResponseContext()
            .status(Status.OK)
            .entity(Collections.emptyList());
    }
}
