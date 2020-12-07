package uk.ac.solent.lunderground;

import org.junit.Test;

import uk.ac.solent.lunderground.model.dao.StationDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LundergroundFacadeTest
{
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
}
