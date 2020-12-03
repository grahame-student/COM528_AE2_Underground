package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;

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
     * Factory used to create the entity manager used to persist the DAO entities
     */
    private static EntityManagerFactory factory = null;

    /**
     * Entity manager used to persist the DAO entities
     */
    private static EntityManager em = null;

    /**
     * DAO singleton used, to access data related to the station entities
     */
    private static StationDao stationDao;

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
}
