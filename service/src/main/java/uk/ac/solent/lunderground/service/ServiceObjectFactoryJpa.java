package uk.ac.solent.lunderground.service;

import uk.ac.solent.lunderground.jpadao.DaoFactoryJpa;
import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.ZoneDao;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;
import uk.ac.solent.lunderground.model.service.ServiceObjectFactory;

public final class ServiceObjectFactoryJpa implements ServiceObjectFactory
{
    /**
     * Instance of the LundergroundServiceFacade to use to access the London underground service.
     */
    private final LundergroundServiceFacade lundergroundFacade;
    /**
     * Instance of the DaoFactory to use when creating Dao objects.
     */
    private DaoFactory daoFactory = null;
    /**
     * Instance of the StationDao to use when accessing Station functionality.
     */
    private StationDao stationDao = null;

    private ZoneDao zoneDao = null;

    /**
     * Public constructor for the JPA version of the ServiceObjectFactory.
     */
    public ServiceObjectFactoryJpa()
    {
        daoFactory = new DaoFactoryJpa();
        stationDao = daoFactory.getStationDao();
        zoneDao = daoFactory.getZoneDao();

        LundergroundFacade facade = new LundergroundFacade();
        facade.setStationDao(stationDao);
        facade.setZoneDao(zoneDao);
        lundergroundFacade = facade;
    }

    @Override
    public LundergroundServiceFacade getLundergroundFacade()
    {
        return lundergroundFacade;
    }
}
