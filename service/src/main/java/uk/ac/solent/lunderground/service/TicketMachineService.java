package uk.ac.solent.lunderground.service;

import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.StationList;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.util.ArrayList;
import java.util.List;

public class TicketMachineService implements TicketMachineFacade
{
    @Override
    public StationList getAllStations()
    {
        final String uri = "http://localhost:8080/lunderground/rest/v1/stations";
        return null;
    }
}
