package uk.ac.solent.lunderground.model.dto;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StationTest
{
    /**
     * ID number to use for tests, specific number is not important.
     */
    private static final long SOME_ID = 123;
    /**
     * Zone number to use for tests, specific number is not important.
     */
    private static final int SOME_ZONE = 3;
    /**
     * Station name to use for tests, specific value is not important.
     */
    private static final String SOME_NAME = "some name";

    /**
     * Check that the Station ID can be set and retrieved.
     */
    @Test
    public void setIdSetsIdToPassedInValue()
    {
        Station station = new Station();

        station.setId(SOME_ID);

        assertThat(station.getId(), equalTo(SOME_ID));
    }

    /**
     * Check that the Station name can be set and retrieved.
     */
    @Test
    public void setNameSetsNameToPassedInValue()
    {
        Station station = new Station();

        station.setName(SOME_NAME);

        assertThat(station.getName(), equalTo(SOME_NAME));
    }

    /**
     * Check that the Station zone can be set and retrieved.
     */
    @Test
    public void setZoneSetsZoneToPassedInValue()
    {
        Station station = new Station();

        station.setZone(SOME_ZONE);

        assertThat(station.getZone(), equalTo(SOME_ZONE));
    }

    /**
     * Check that the String representation is formatted as expected.
     */
    @Test
    public void toStringReturnsInformativeString()
    {
        final String EXPECTED_STRING = "Station{name=null, zone=0, id=null}";
        Station station = new Station();

        assertThat(station.toString(), equalTo(EXPECTED_STRING));
    }
}
