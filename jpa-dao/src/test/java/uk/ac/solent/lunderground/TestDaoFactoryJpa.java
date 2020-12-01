package uk.ac.solent.lunderground;

import org.junit.Test;
import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class TestDaoFactoryJpa
{
    @Test
    public void GetStationDao_Returns_StationDaoJpaInstance()
    {
        DaoFactory factory = new DaoFactoryJpa();

        StationDao stationDao = factory.getStationDao();

        assertThat(stationDao, instanceOf(StationDaoJpa.class));
    }
}
