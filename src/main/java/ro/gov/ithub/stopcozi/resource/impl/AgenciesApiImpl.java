package ro.gov.ithub.stopcozi.resource.impl;

import io.dropwizard.hibernate.UnitOfWork;
import ro.gov.ithub.stopcozi.resource.api.AgenciesApi;
import ro.gov.ithub.stopcozi.dao.AgencyDao;
import ro.gov.ithub.stopcozi.model.Adaptors;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@Singleton
public class AgenciesApiImpl implements AgenciesApi {

    private AgencyDao agencyDao;

    @Inject
    public AgenciesApiImpl(AgencyDao agencyDao) {
        this.agencyDao = agencyDao;
    }

    @Override
    @UnitOfWork(readOnly = true)
    public Response listAgencies(String countyCode) {
        return Response.status(Response.Status.ACCEPTED).entity(
            agencyDao.listByCounty(countyCode).stream().map(Adaptors::from).collect(Collectors.toList())
        ).build();
    }
}
