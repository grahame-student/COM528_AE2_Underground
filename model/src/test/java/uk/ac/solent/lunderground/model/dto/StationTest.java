package uk.ac.solent.lunderground.model.dto;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StationTest
{
    private static final long SOME_ID = 123;
    private static final int SOME_ZONE = 123;
    private static final String SOME_NAME = "some name";

    @Test
    public void setIdSetsIdToPassedInValue()
    {
        Station station = new Station();

        station.setId(SOME_ID);

        assertThat(station.getId(), equalTo(SOME_ID));
    }

    @Test
    public void setNameSetsNameToPassedInValue()
    {
        Station station = new Station();

        station.setName(SOME_NAME);

        assertThat(station.getName(), equalTo(SOME_NAME));
    }

    @Test
    public void setZoneSetsZoneToPassedInValue()
    {
        Station station = new Station();

        station.setZone(SOME_ZONE);

        assertThat(station.getZone(), equalTo(SOME_ZONE));
    }

    @Test
    public void toStringReturnsInformativeString()
    {
        String EXPECTED_STRING = "Station{name=null, zone=0, id=null}";
        Station station = new Station();

        assertThat(station.toString(), equalTo(EXPECTED_STRING));
    }
}
