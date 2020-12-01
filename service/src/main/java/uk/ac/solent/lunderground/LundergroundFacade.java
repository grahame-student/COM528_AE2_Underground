package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

import java.util.List;

public final class LundergroundFacade implements LundergroundServiceFacade
{
    private StationDao stationDao = null;

    public void setStationDao(StationDao stationDao)
    {
        this.stationDao = stationDao;
    }

    @Override
    public List<Station> getAllStations()
    {
        return stationDao.retrieveAll();
    }
}
