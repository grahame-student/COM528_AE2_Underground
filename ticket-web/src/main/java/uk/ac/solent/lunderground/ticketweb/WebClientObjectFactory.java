package uk.ac.solent.lunderground.ticketweb;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uk.ac.solent.lunderground.clientrest.ConfigurationPoller;
import uk.ac.solent.lunderground.clientrest.RestClientObjectFactory;
import uk.ac.solent.lunderground.model.service.ServiceObjectFactory;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

@Component
public class WebClientObjectFactory
{
    /**
     * Time to wait before requesting first config update
     */
    private static final long INITIAL_DELAY = 0;
    /**
     * Time to wait between each subsequent config update request
     */
    private static final long POLL_PERIOD = 30;

    private static TicketMachineFacade ticketFacade = null;
    private static ServiceObjectFactory clientObjectFactory = null;
    private static ConfigurationPoller configPoller = null;

    public static String getTicketMachineUuid()
    {
        return configPoller.getTicketMachineUuid();
    }

    public static TicketMachineFacade getServiceFacade()
    {
        if (ticketFacade == null)
        {
            synchronized (WebClientObjectFactory.class)
            {
                if (ticketFacade == null)
                {
                    clientObjectFactory = new RestClientObjectFactory();
                    ticketFacade = clientObjectFactory.getTicketMachineFacade();

                    configPoller = new ConfigurationPoller(ticketFacade);
                    configPoller.init(INITIAL_DELAY, POLL_PERIOD);
                }
            }
        }
        return ticketFacade;
    }

//    @EventListener
//    public void onContextStarted(ContextStartedEvent event)
//    {
//
//    }
//
//    @EventListener
//    public void onContextStopped(ContextStoppedEvent event)
//    {
//
//    }
}
