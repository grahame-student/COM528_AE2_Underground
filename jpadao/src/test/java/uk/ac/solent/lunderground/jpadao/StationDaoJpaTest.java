package uk.ac.solent.lunderground.jpadao;

import org.junit.Before;
import org.junit.Test;
import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;

public class StationDaoJpaTest
{
    /*
     *  These tests are not strictly unit tests as we are testing
     *  that station instances are being persisted to the disk rather
     *  than mocking the entity manager responsible and checking that
     *  it is being used correctly. As a result these tests are relatively
     *  slow
     */

    /**
     * Station name used for test purposes, specific value is unimportant.
     */
    private static final String SOME_STATION = "some station";
    /**
     * Station name used for test purposes, name helps identify specific instance.
     */
    private static final String STATION_1 = "Station 1";
    /**
     * Station name used for test purposes, name helps identify specific instance.
     */
    private static final String STATION_2 = "Station 2";

    /**
     * Zone number used for testing purposes, specific value is unimportant.
     */
    private static final int SOME_ZONE = 1;

    /**
     * Zero items in a list.
     */
    private static final int ZERO_ITEMS = 0;
    /**
     * 2 items in a list.
     */
    private static final int TWO_ITEMS = 2;

    /**
     * Index of the first item in a list.
     */
    private static final int FIRST_ITEM = 0;

    /**
     * Used to hold a reference to the StationDao instance being tested.
     */
    private StationDao stationDao;

    /**
     * Setup used to initialise the test environment before each test case.
     * - Initialises the StationDao instance to be tested
     * - Removes all of the stations from the persistence database
     */
    @Before
    public void setup()
    {
        // Remove everything from the database before each test to ensure that
        // each unit test is as atomic as possible
        DaoFactory factory = new DaoFactoryJpa();
        stationDao = factory.getStationDao();
        stationDao.deleteAll();
    }

    /**
     * Check that the addStation method saves the passed in station to the database.
     */
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

    /**
     * Check that the persisted Stations can be retrieved from the database.
     */
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

        List<Station> stationList = stationDao.retrieveAll();
        assertThat(stationList.size(), equalTo(TWO_ITEMS));
    }

    /**
     * Check that all stations can be removed from the persistence database.
     */
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

        List<Station> stationList = stationDao.retrieveAll();
        assertThat(stationList.size(), equalTo(ZERO_ITEMS));
    }

    /**
     * Check that only the station with the specified ID is removed from the database.
     */
    @Test
    public void deleteRemovesStationWithPassedInIdFromTheDatabase()
    {
        DaoFactory factory = new DaoFactoryJpa();
        stationDao = factory.getStationDao();
        Station testStation = new Station();
        testStation.setName(STATION_1);
        stationDao.addStation(testStation);
        testStation = new Station();
        testStation.setName(STATION_2);
        stationDao.addStation(testStation);
        testStation = stationDao.retrieveAll().get(FIRST_ITEM);

        stationDao.deleteStation(testStation.getId());

        List<Station> stationList = stationDao.retrieveAll();
        assertThat(stationList, not(hasItem(testStation)));
    }
}
