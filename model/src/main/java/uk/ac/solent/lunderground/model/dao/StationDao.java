package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

public interface StationDao
{
    /**
     * Get a list of all stations that exist.
     *
     * @return List of Station objects
     */
    List<Station> retrieveAll();

    /**
     * Add the passed in Station to the persistence database.
     *
     * @param newStation The Station to be saved to the database
     */
    void addStation(Station newStation);

    /**
     * Remove the station with passed in ID from the database.
     *
     * @param stationId The Long ID of the station to be removed
     */
    void deleteStation(long stationId);

    /**
     * Remove all Station instances from the database.
     */
    void deleteAll();
}
