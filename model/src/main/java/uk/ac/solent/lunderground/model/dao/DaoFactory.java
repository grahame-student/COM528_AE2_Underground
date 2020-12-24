package uk.ac.solent.lunderground.model.dao;

public interface DaoFactory
{
    /**
     * Get a reference to a StationDao instance.
     *
     * @return return a concrete instance of a StationDao
     */
    StationDao getStationDao();
    ZoneDao getZoneDao();
}
