package ro.gov.ithub.stopcozi.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
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
}
