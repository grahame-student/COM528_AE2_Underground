package uk.ac.solent.lunderground.service;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

import java.util.List;

public final class LundergroundFacade implements LundergroundServiceFacade
{
    /**
     * StationDao instance used to access the database implementation.
     */
    private StationDao stationDao = null;

    /**
     * Set the StationDao instance to use for accessing the database layer.
     *
     * @param newStationDao StationDao instance to set
     */
    public void setStationDao(final StationDao newStationDao)
    {
        this.stationDao = newStationDao;
    }

    @Override
    public List<Station> getAllStations()
    {
        return stationDao.retrieveAll();
    }

    @Override
    public void addStation(final String stationName, final int zoneNumber)
    {
        Station station = new Station();
        station.setName(stationName);
        station.setZone(zoneNumber);
        stationDao.addStation(station);
    }

    @Override
    public void deleteStation(final int stationId)
    {

    }

    @Override
    public void deleteAll()
    {
        stationDao.deleteAll();
    }
}
