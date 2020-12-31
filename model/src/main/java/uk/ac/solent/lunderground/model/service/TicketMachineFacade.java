package uk.ac.solent.lunderground.model.service;

import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;

public interface TicketMachineFacade
{
    TicketMachineConfig getTicketMachineConfig(String uuid);

    /**
     * Create and add a new ticket machine instance.
     * @param uuid The uuid of the new machine to create and add.
     */
    void addTicketMachine(String uuid);
}
