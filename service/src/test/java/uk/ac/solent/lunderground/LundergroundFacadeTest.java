package uk.ac.solent.lunderground;

import org.junit.Test;

import org.mockito.ArgumentCaptor;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.service.LundergroundFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LundergroundFacadeTest
{
    private static final String SOME_NAME = "some name";
    private static final int SOME_ZONE = 2;

    /**
     * Check that the configured StationDao is used to access the underlying database.
     */
    @Test
    public void setStationDaoSetsStationDaoToUseWhenRetrievingStationDetails()
    {
        StationDao mockStationDao = mock(StationDao.class);
        LundergroundFacade facade = new LundergroundFacade();

        facade.setStationDao(mockStationDao);
        facade.getAllStations();

        verify(mockStationDao).retrieveAll();
    }

    @Test
    public void addStationUsesStationDaoToAddStationWithPassedInName()
    {
        StationDao mockStationDao = mock(StationDao.class);
        LundergroundFacade facade = new LundergroundFacade();
        facade.setStationDao(mockStationDao);
        ArgumentCaptor<Station> args = ArgumentCaptor.forClass(Station.class);

        facade.addStation(SOME_NAME, SOME_ZONE);

        verify(mockStationDao).addStation(args.capture());
        assertThat(args.getValue().getName(), equalTo(SOME_NAME));
    }

    @Test
    public void addStationUsesStationDaoToAddStationWithPassedInZone()
    {
        StationDao mockStationDao = mock(StationDao.class);
        LundergroundFacade facade = new LundergroundFacade();
        facade.setStationDao(mockStationDao);
        ArgumentCaptor<Station> args = ArgumentCaptor.forClass(Station.class);

        facade.addStation(SOME_NAME, SOME_ZONE);

        verify(mockStationDao).addStation(args.capture());
        assertThat(args.getValue().getZone(), equalTo(SOME_ZONE));
    }

    @Test
    public void deleteAllUsesStationDaoToRemoveAllStations()
    {
        StationDao mockStationDao = mock(StationDao.class);
        LundergroundFacade facade = new LundergroundFacade();
        facade.setStationDao(mockStationDao);

        facade.deleteAll();

        verify(mockStationDao).deleteAll();
    }
}
