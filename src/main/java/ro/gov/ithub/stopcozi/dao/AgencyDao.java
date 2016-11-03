package ro.gov.ithub.stopcozi.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import ro.gov.ithub.stopcozi.model.repo.Agency;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AgencyDao extends AbstractDAO<Agency> {
    @Inject
    public AgencyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Agency> listByCounty(String countryCode){
        return list(criteria().add(Restrictions.eq("countyCode", countryCode)));
    }
}
