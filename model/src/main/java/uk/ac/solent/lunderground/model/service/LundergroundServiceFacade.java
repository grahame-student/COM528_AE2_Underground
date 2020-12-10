package uk.ac.solent.lunderground.model.service;

import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

public interface LundergroundServiceFacade
{
    /**
     * Get a list of all of the Stations in the London underground system.
     *
     * @return List of Stations
     */
    List<Station> getAllStations();

    /**
     * Add a Station to the London underground system.
     *
     * @param stationName String containing the name of the station to add
     * @param zoneNumber  Integer containing the zone number that station is in
     */
    void addStation(String stationName, int zoneNumber);

    /**
     * Remove the station with the specified ID.
     *
     * @param stationId The ID of the station to be removed.
     */
    void deleteStation(long stationId);

    /**
     * Remove all stations from the London underground systems.
     */
    void deleteAll();
}
