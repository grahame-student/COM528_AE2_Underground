package uk.ac.solent.lunderground.jaxbdao;

import org.junit.Test;
import uk.ac.solent.lunderground.model.dto.Station;

import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests for the JAXB Station DAO implementation.
 * These are closer to integration tests as we are reading from and writing to real test files and not mocked versions.
 */
public class StationDaoJaxbTest
{
    /**
     * Path to test XML file unmarshalling. Doesn't need to be super robust for testing purposes.
     *
     * Contains 2 stations:
     *     Abbey Road, Zone 2
     *     Acton Town, Zone 3
     */
    private final URL testXmlUrl = getClass().getClassLoader().getResource("test.xml");
    private final URL testXml2Url = getClass().getClassLoader().getResource("test2.xml");

    /**
     * Expected name of the first station in the StationList.
     */
    private static final String STATION_1_NAME = "Abbey Road";

    /**
     * Expected name of the first station in the StationList.
     */
    private static final String STATION_2_NAME = "Acton Town";

    /**
     * Expected zone of the first station in the StationList.
     */
    private static final int STATION_1_ZONE = 2;

    /**
     * Expected zone of the first station in the StationList.
     */
    private static final int STATION_2_ZONE = 3;

    /**
     * First element in a collection.
     */
    private static final int FIRST_ELEMENT = 0;

    /**
     * Number of elements to expect in a collection.
     */
    private static final int TWO_ELEMENTS = 2;

    /**
     * Check that the test.xml can be unmarshalled into a List of all Stations.
     */
    @Test
    public void LoadUnmarshalsStationsFromXmlFile()
    {
        assert testXmlUrl != null;
        StationDaoJaxb dao = new StationDaoJaxb(testXmlUrl);

        dao.load();
        List<?> list = dao.retrieveAll();

        assertThat(list.size(), equalTo(TWO_ELEMENTS));
    }

    /**
     * Check that the station name is unmarshalled correctly.
     */
    @Test
    public void LoadUnmarshalsStationNameFromXmlFile()
    {
        assert testXmlUrl != null;
        StationDaoJaxb dao = new StationDaoJaxb(testXmlUrl);

        dao.load();
        List<Station> list = dao.retrieveAll();

        Station firstStation = list.get(FIRST_ELEMENT);
        assertThat(firstStation.getName(), equalTo(STATION_1_NAME));
    }

    /**
     * Check that the station zone is unmarshalled correctly.
     */
    @Test
    public void LoadUnmarshalsStationZoneFromXmlFile()
    {
        assert testXmlUrl != null;
        StationDaoJaxb dao = new StationDaoJaxb(testXmlUrl);

        dao.load();
        List<Station> list = dao.retrieveAll();

        Station firstStation = list.get(FIRST_ELEMENT);
        assertThat(firstStation.getZone(), equalTo(STATION_1_ZONE));
    }

    /**
     * Check that we can round trip a StationList using the DAO.
     */
    @Test
    public void SaveMarshalsStationListToXMLFile()
    {
        assert testXml2Url != null;
        StationDaoJaxb dao = new StationDaoJaxb(testXml2Url);

        Station station1 = new Station();
        station1.setName(STATION_1_NAME);
        station1.setZone(STATION_1_ZONE);
        dao.addStation(station1);
        Station station2 = new Station();
        station2.setName(STATION_2_NAME);
        station2.setZone(STATION_2_ZONE);
        dao.addStation(station2);
        dao.save();
        List<Station> before = dao.retrieveAll();

        dao.load();
        List<Station> after = dao.retrieveAll();

        assertThat(after, equalTo(before));
    }
}
