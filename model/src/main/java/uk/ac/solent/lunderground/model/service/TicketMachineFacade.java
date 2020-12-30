package uk.ac.solent.lunderground.model.service;

import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;

public interface TicketMachineFacade
{
    TicketMachineConfig getTicketMachineConfig(String uuid);
}
