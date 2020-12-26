package uk.ac.solent.lunderground.service;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.ZoneDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

import java.util.List;

public final class LundergroundFacade implements LundergroundServiceFacade
{
    /**
     * StationDao instance used to access the database implementation.
     */
    private StationDao stationDao = null;

    private ZoneDao zoneDao = null;

    /**
     * Set the StationDao instance to use for accessing the database layer.
     *
     * @param newStationDao StationDao instance to set
     */
    public void setStationDao(final StationDao newStationDao)
    {
        this.stationDao = newStationDao;
    }

    /**
     * Set the ZoneDao instance to use for accessing the database layer.
     *
     * @param newZoneDao ZoneDao to use
     */
    public void setZoneDao(ZoneDao newZoneDao)
    {
        this.zoneDao = newZoneDao;
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
    public void deleteStation(final long stationId)
    {
        stationDao.deleteStation(stationId);
    }

    @Override
    public void deleteAll()
    {
        stationDao.deleteAll();
    }

    @Override
    public Station getStation(final String stationName)
    {
        return stationDao.getStation(stationName);
    }

    @Override
    public Station getStation(final long stationId)
    {
        return stationDao.getStation(stationId);
    }

    @Override
    public List<Integer> getAllZones()
    {
        return zoneDao.retrieveAll();
    }

    @Override
    public void updateStation(long stationId, String newName, int newZone)
    {
        Station station = new Station();
        station.setId(stationId);
        station.setName(newName);
        station.setZone(newZone);
        stationDao.updateStation(station);
    }
}
