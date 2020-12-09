package uk.ac.solent.lunderground.model.service;

import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

public interface LundergroundServiceFacade
{
    /**
     * Get a list of all the Stations in the London underground system.
     *
     * @return List of Stations containing all the stations
     */
    List<Station> getAllStations();

    void addStation(String stationName, int zoneNumber);
    void deleteAll();
}
