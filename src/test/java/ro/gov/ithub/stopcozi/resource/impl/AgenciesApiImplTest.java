package ro.gov.ithub.stopcozi.resource.impl;

import com.google.common.collect.ImmutableList;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.*;
import ro.gov.ithub.stopcozi.dao.AgencyDao;
import ro.gov.ithub.stopcozi.model.repo.Agency;

import javax.ws.rs.core.GenericType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by NiBo on 11/7/2016.
 */
public class AgenciesApiImplTest {

    private static final AgencyDao dao = mock(AgencyDao.class);

    private Agency agency;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new Object())
//            .addResource(new AgenciesApiImpl(dao))//is missing from this version of project
            .build();

    @Before
    public void setUp() {
        agency = new Agency();
        agency.setName("AAA");
        agency.setCountyCode("GL");
    }

    @After
    public void tearDown(){
        reset(dao);
    }

    //ignored because of missing AgenciesApiImpl
    @Ignore
    @Test
    public void testListByCounty() throws Exception {
        final ImmutableList<Agency> agencies = ImmutableList.of(agency);
        when(dao.listByCounty("GL")).thenReturn(agencies);
        final List<Agency> response =  resources.client().target("/agencies/GL").request().get(new GenericType<List<Agency>>(){});
        verify(dao).listByCounty("GL");
        assertThat(response).containsAll(agencies);
        assertThat(response).hasSize(1);
        assertThat(response.get(0).getName()).isEqualTo("AAA");
    }
}