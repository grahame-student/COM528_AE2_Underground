package uk.ac.solent.lunderground.service;

import uk.ac.solent.lunderground.jaxbdao.DaoFactoryJaxb;
import uk.ac.solent.lunderground.jpadao.DaoFactoryJpa;
import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.TicketMachineDao;
import uk.ac.solent.lunderground.model.dao.ZoneDao;
import uk.ac.solent.lunderground.model.service.DeveloperFacade;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;
import uk.ac.solent.lunderground.model.service.ServiceObjectFactory;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

public final class ServiceObjectFactoryImpl implements ServiceObjectFactory
{
    /**
     * Instance of the LundergroundServiceFacade to use to access the London underground service.
     */
    private final LundergroundServiceFacade lundergroundFacade;

    /**
     * Special facade that exposes additional developer / admin only features
     */
    private final DeveloperFacade developerFacade;
    /**
     * Instance of the DaoFactory to use when creating Dao objects.
     */
    private DaoFactory daoFactory = null;
    /**
     * Instance of the StationDao to use when accessing Station functionality.
     */
    private StationDao stationDao = null;
    /**
     * Instance of the ZoneDao to use when accessing Zone functionality.
     */
    private ZoneDao zoneDao = null;

    /**
     * Instance of the TicketMachineDao to use when accessing TicketMachine functionality.
     */
    private TicketMachineDao ticketMachineDao = null;

    /**
     * Instance of the DaoFactory to use when creating Dao objects for dev purposes.
     */
    private DaoFactory devDaoFactory = null;
    /**
     * Instance of the StationDao to use when accessing Station functionality for dev purposes.
     */
    private StationDao devStationDao = null;

    /**
     * Public constructor for the JPA version of the ServiceObjectFactory.
     */
    public ServiceObjectFactoryImpl()
    {
        LundergroundFacade facade = new LundergroundFacade();
        lundergroundFacade = facade;
        developerFacade = facade;

        initLundergroundFacade(facade);
        initDeveloperFacade(facade);
    }

    private void initLundergroundFacade(final LundergroundFacade facade)
    {
        daoFactory = new DaoFactoryJpa();
        stationDao = daoFactory.getStationDao();
        zoneDao = daoFactory.getZoneDao();
        ticketMachineDao = daoFactory.getTicketMachineDao();

        facade.setStationDao(stationDao);
        facade.setZoneDao(zoneDao);
        facade.setTicketMachineDao(ticketMachineDao);
    }

    private void initDeveloperFacade(final DeveloperFacade facade)
    {
        devDaoFactory = new DaoFactoryJaxb();
        devStationDao = devDaoFactory.getStationDao();
        facade.setDevStationDao(devStationDao);
    }

    @Override
    public LundergroundServiceFacade getLundergroundFacade()
    {
        return lundergroundFacade;
    }

    @Override
    public DeveloperFacade getDeveloperFacade()
    {
        return developerFacade;
    }

    @Override
    public TicketMachineFacade getTicketMachineFacade()
    {
        return null;
    }
}
