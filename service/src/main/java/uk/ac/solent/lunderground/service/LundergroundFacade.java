package uk.ac.solent.lunderground.service;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.TicketMachineDao;
import uk.ac.solent.lunderground.model.dao.ZoneDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.TicketMachine;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.DeveloperFacade;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

import java.util.List;

public final class LundergroundFacade implements LundergroundServiceFacade, DeveloperFacade
{
    /**
     * StationDao instance used to access the database implementation.
     */
    private StationDao stationDao = null;

    /**
     * ZoneDao instance used to access the database implementation.
     */
    private ZoneDao zoneDao = null;

    /**
     * TicketMachineDao instance used to access the database implementation.
     */
    private TicketMachineDao ticketMachineDao = null;

    /**
     * StationDao instance used during development activities.
     */
    private StationDao devStationDao = null;

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
    public void setZoneDao(final ZoneDao newZoneDao)
    {
        this.zoneDao = newZoneDao;
    }

    public void setTicketMachineDao(final TicketMachineDao newTicketMachineDao)
    {
        this.ticketMachineDao = newTicketMachineDao;
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
    public void deleteAllStations()
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
    public TicketMachineConfig getTicketMachineConfig(String uuid)
    {
        TicketMachineConfig config = new TicketMachineConfig();
        TicketMachine ticketMachine = ticketMachineDao.getTicketMachine(uuid);

        if (ticketMachine != null)
        {
            config.setUuid(ticketMachine.getUuid());
            config.setStation(ticketMachine.getStation());
        }
        config.setStationList(stationDao.retrieveAll());
        return config;
    }

    @Override
    public void addTicketMachine(TicketMachine newTicketMachine)
    {
        ticketMachineDao.addTicketMachine(newTicketMachine);
    }

//    @Override
//    public TicketMachine getTicketMachine(String uuid)
//    {
//        return ticketMachineDao.getTicketMachine(uuid);
//    }

    @Override
    public void updateStation(final long stationId, final String newName, final int newZone)
    {
        Station station = new Station();
        station.setId(stationId);
        station.setName(newName);
        station.setZone(newZone);
        stationDao.updateStation(station);
    }

    @Override
    public void setDevStationDao(StationDao newStationDao)
    {
        this.devStationDao = newStationDao;
    }

    @Override
    public void initStationList()
    {
        List<Station> stationList = devStationDao.retrieveAll();
        stationDao.deleteAll();
        for (Station station: stationList)
        {
            stationDao.addStation(station);
        }
    }
}
