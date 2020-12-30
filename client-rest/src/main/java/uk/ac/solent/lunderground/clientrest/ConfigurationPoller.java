package uk.ac.solent.lunderground.clientrest;

import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.util.UUID;

public class ConfigurationPoller
{
    private String ticketMachineUuid = UUID.randomUUID().toString();

    public ConfigurationPoller(TicketMachineFacade ticketFacade)
    {

    }

    public String getTicketMachineUuid()
    {
        return ticketMachineUuid;
    }

    public void setTicketMachineUuid(String newTicketMachineUuid)
    {
        this.ticketMachineUuid = newTicketMachineUuid;
    }

    public void init(long initialDelay, long pollPeriod)
    {
    }
}
