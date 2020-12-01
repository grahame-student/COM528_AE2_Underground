package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactoryJpa implements DaoFactory
{
    private static final String PERSISTENCE_UNIT_NAME = "lundergroundPersistence";

    private static EntityManagerFactory factory = null;
    private static EntityManager em = null;
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
