package uk.ac.solent.lunderground;

import org.junit.Before;
import org.junit.Test;
import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class StationDaoJpaTest
{
    /*
     *  These tests are not strictly unit tests as we are testing
     *  that station instances are being persisted to the disk rather
     *  than mocking the entity manager responsible and checking that
     *  it is being used correctly. As a result these tests are relatively
     *  slow
     */

    private static final String SOME_STATION = "some station";
    private static final int SOME_ZONE = 1;

    private static final int ZERO_ITEMS = 0;
    private static final int TWO_ITEMS = 2;

    private StationDao stationDao;

    @Before
    public void Setup()
    {
        // Remove everything from the database before each test to ensure that
        // each unit test is as atomic as possible
        DaoFactory factory = new DaoFactoryJpa();
        stationDao = factory.getStationDao();
        stationDao.deleteAll();
    }

    @Test
    public void addStationAddsPassedInStationToPersistenceDatabase()
    {
        Station testStation = new Station();
        testStation.setName(SOME_STATION);
        testStation.setZone(SOME_ZONE);
        stationDao.addStation(testStation);

        List<Station> stationList = stationDao.retrieveAll();
        assertThat(stationList, hasItem(testStation));
    }

    @Test
    public void retrieveAllReturnsListOfAddedStations()
    {
        DaoFactory factory = new DaoFactoryJpa();
        stationDao = factory.getStationDao();

        // We need to create 2 station instances because if we add the same instance
        // twice the persistence layer treats it as the same item and we only get
        // one item in the database
        Station testStation = new Station();
        stationDao.addStation(testStation);
        testStation = new Station();
        stationDao.addStation(testStation);

        List<?> stationList = stationDao.retrieveAll();
        assertThat(stationList.size(), equalTo(TWO_ITEMS));
    }

    @Test
    public void deleteAllRemovesAllStationsFromTheDatabase()
    {
        DaoFactory factory = new DaoFactoryJpa();
        stationDao = factory.getStationDao();
        Station testStation = new Station();
        stationDao.addStation(testStation);
        testStation = new Station();
        stationDao.addStation(testStation);

        stationDao.deleteAll();

        List<?> stationList = stationDao.retrieveAll();
        assertThat(stationList.size(), equalTo(ZERO_ITEMS));
    }
}
