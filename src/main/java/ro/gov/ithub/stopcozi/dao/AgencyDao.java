package ro.gov.ithub.stopcozi.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ro.gov.ithub.stopcozi.model.repo.Agency;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
public class AgencyDao extends AbstractDAO<Agency> {
    @Inject
    public AgencyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Agency> listByCounty(String countryCode){
        //return list(criteria().add(Restrictions.eq("countyCode", countryCode)));
        Agency agency = new Agency();
        agency.setName("ADR");
        agency.setCountyCode("IS");
        agency.setId(100001L);

        return Collections.singletonList(agency);
    }

    //this method was added in order to make integration test: AgencyDaoTest
    public List<Agency> listByCountyUsedInTest(String countryCode) {
        return list(criteria().add(Restrictions.eq("countyCode", countryCode)));
    }

    //this method was added in order to make integration test: AgencyDaoTest
    public Agency save(Agency agency) {
        return persist(agency);
    }
}
