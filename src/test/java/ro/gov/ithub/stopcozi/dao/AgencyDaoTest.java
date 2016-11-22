package ro.gov.ithub.stopcozi.dao;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by NiBo on 11/7/2016.
 */
public class AgencyDaoTest extends DAOTests {

    AgencyDao agencyDao;

    @Before
    public void initialize() {
        agencyDao = new AgencyDao(sessionFactory);

        // Delete all the old junk...
        getSession().beginTransaction();
        Query q = getSession().createQuery("delete from Agency");
        q.executeUpdate();
        getSession().getTransaction().commit();
    }

    @Test
    public void testAgencies() throws Exception {
        getSession().beginTransaction();

        for(int i = 0; i < 10; i++) {
            ro.gov.ithub.stopcozi.model.repo.Agency t = new ro.gov.ithub.stopcozi.model.repo.Agency();
            t.setCountyCode("GL");
            t.setName(RandomStringUtils.randomAlphanumeric(10));
            agencyDao.save(t);
        }

        assertEquals(agencyDao.listByCountyUsedInTest("GL").size(), 10);

        getSession().getTransaction().commit();
    }

}