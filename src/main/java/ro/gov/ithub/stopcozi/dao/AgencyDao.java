package ro.gov.ithub.stopcozi.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ro.gov.ithub.stopcozi.model.repo.Agency;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AgencyDao extends AbstractDAO<Agency> {
    @Inject
    public AgencyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Long save(Agency agency){
        Agency persistedAgency = persist(agency);

        return persistedAgency.getInternalId();
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
