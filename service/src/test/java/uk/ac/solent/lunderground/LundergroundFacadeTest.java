package uk.ac.solent.lunderground;

import org.junit.Test;
import static org.mockito.Mockito.*;

import uk.ac.solent.lunderground.model.dao.StationDao;

public class LundergroundFacadeTest
{
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
