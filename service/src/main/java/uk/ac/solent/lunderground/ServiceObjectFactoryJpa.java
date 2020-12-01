package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;
import uk.ac.solent.lunderground.model.service.ServiceObjectFactory;

public final class ServiceObjectFactoryJpa implements ServiceObjectFactory
{
    private final LundergroundServiceFacade lundergroundFacade;
    private DaoFactory daoFactory = null;
    private StationDao stationDao = null;

    public ServiceObjectFactoryJpa()
    {
        daoFactory = new DaoFactoryJpa();
        stationDao = daoFactory.getStationDao();

        LundergroundFacade facade = new LundergroundFacade();
        facade.setStationDao(stationDao);
        lundergroundFacade = facade;
    }

    @Override
    public LundergroundServiceFacade getLundergroundFacade()
    {
        return lundergroundFacade;
    }
}
