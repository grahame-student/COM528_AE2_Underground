package uk.ac.solent.lunderground.jaxbdao;

import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.ZoneDao;
import uk.ac.solent.lunderground.simpledao.ZoneDaoSimple;

public class DaoFactoryJaxb implements DaoFactory
{
    /**
     * URL to the master station list.
     */
    private final String stationXml = "londonStations.xml";

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
                        assert stationXml != null;
                        StationDaoJaxb stationDaoJaxb = new StationDaoJaxb(stationXml);
                        stationDaoJaxb.load();
                        stationDao = stationDaoJaxb;
                    }
                    catch (Exception ex)
                    {
                        throw new RuntimeException("problem creating StationDaoJaxb ", ex);
                    }
                }
            }
        }
        return stationDao;
    }

    @Override
    public ZoneDao getZoneDao()
    {
        if (zoneDao == null)
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
