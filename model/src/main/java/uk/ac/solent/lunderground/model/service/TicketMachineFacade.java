package uk.ac.solent.lunderground.model.service;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.TicketPricingDao;
import uk.ac.solent.lunderground.model.dto.Rate;
import uk.ac.solent.lunderground.model.dto.Ticket;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;

import java.util.Date;

public interface TicketMachineFacade
{
    /**
     * register a callback method to run when the ticket machine config is changed.
     * @param configChangedCallback the method to run when the callback is triggered
     */
    void ticketMachineConfigChanged(Runnable configChangedCallback);

    TicketMachineConfig getTicketMachineConfig(String uuid);

    /**
     * Create and add a new ticket machine instance.
     * @param uuid The uuid of the new machine to create and add.
     */
    void addTicketMachine(String uuid);

    /**
     * Update the ticket machine with the passed in uuid.
     * @param uuid uuid of the ticket machine to update
     * @param stationName name of the station to set in the ticket machine
     */
    void updateTicketMachine(String uuid, String stationName);

    StationDao getStationDao();
    TicketPricingDao getTicketPricingDao();
    Ticket getTicket(String startStation, String destStation);
    Ticket getTicket(String startStation, String destStation, Date issueDate);
    String encodeTicket(Ticket ticket);
}
