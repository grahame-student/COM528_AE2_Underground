package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

public interface StationDao
{
    List<Station> retrieveAll();
}
