package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

public interface StationDao
{
    /**
     * Get a list of all stations that exist.
     * @return
     */
    List<Station> retrieveAll();
}
