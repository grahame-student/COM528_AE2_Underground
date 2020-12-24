package uk.ac.solent.lunderground.jpadao;

import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.ZoneDao;

import uk.ac.solent.lunderground.simpledao.ZoneDaoSimple;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class DaoFactoryJpa implements DaoFactory
{
    /**
     * Name of the persistence unit as required by the entity manager.
     */
    private static final String PERSISTENCE_UNIT_NAME = "lundergroundPersistence";

    /**
     * Factory used to create the entity manager used to persist the DAO entities.
     */
    private static EntityManagerFactory factory = null;

    /**
     * Entity manager used to persist the DAO entities.
     */
    private static EntityManager em = null;

    /**
     * DAO singleton used to access data related to the station entities.
     */
    private static StationDao stationDao;

    /**
     * DAO singleton used to access data related to the zones.
     */
    private static ZoneDao zoneDao;

    @Override
    public StationDao getStationDao()
    {
        if (stationDao == null)
        {
            synchronized (this)
            {
                if (stationDao == null)
                {
                    try
                    {
                        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                        em = factory.createEntityManager();
                        stationDao = new StationDaoJpa(em);
                    }
                    catch (Exception ex)
                    {
                        throw new RuntimeException("problem creating StationDaoJpa ", ex);
                    }
                }
            }
        }
        return stationDao;
    }

    @Override
    public ZoneDao getZoneDao()
    {
        if(zoneDao == null)
        {
            synchronized (this)
            {
                if (zoneDao == null)
                {
                    // This is not ideal but creating a new DaoFactory in the SimpleDao module
                    // just for this one simple DAO instance would consume a lot of time for
                    // little immediate benefit. Should more simple DAOs be required then
                    // this decision can be revisited.
                    zoneDao = new ZoneDaoSimple();
                }
            }
        }
        return zoneDao;
    }
}
