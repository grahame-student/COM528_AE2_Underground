package uk.ac.solent.lunderground.model.service;

import uk.ac.solent.lunderground.model.dto.Station;

import java.util.List;

public interface LundergroundServiceFacade
{
    List<Station> getAllStations();
}
