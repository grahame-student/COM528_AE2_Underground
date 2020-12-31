package uk.ac.solent.lunderground.clientrest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.ac.solent.lunderground.model.dto.PricingSchedule;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ConfigurationPoller
{
    /**
     * Logger instance for the ConfigurationPoller implementation.
     */
    private static final Logger LOG = LogManager.getLogger(ConfigurationPoller.class);

    TicketMachineFacade facade = null;

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> futureTask = null;
    private String ticketMachineUuid = null;
    private boolean pollerInitialised = false;
    private Date lastUpdateAttempt;
    private Date lastUpdateTime;
    private String stationName;
    private int stationZone;

    public ConfigurationPoller(TicketMachineFacade ticketFacade)
    {
        facade = ticketFacade;
    }

    public String getTicketMachineUuid()
    {
        return ticketMachineUuid;
    }

    public void setTicketMachineUuid(String newTicketMachineUuid)
    {
        this.ticketMachineUuid = newTicketMachineUuid;
    }

    public Date getLastUpdateAttempt()
    {
        return lastUpdateAttempt;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public String getStationName()
    {
        return stationName;
    }

    public int getStationZone()
    {
        return stationZone;
    }

    /**
     * Start polling periodically for an up to date ticket machine configuration.
     * @param initialDelay time to wait for first attempt
     * @param pollPeriod time between each subsequent attempt
     */
    public void init(long initialDelay, long pollPeriod)
    {
        if (!pollerInitialised) synchronized (this)
        {
            scheduler.scheduleAtFixedRate(this::getLatestConfig, initialDelay, pollPeriod, TimeUnit.SECONDS);
            pollerInitialised = true;
        }
        else
        {
            LOG.debug("init called when configuration poller already initialised");
        }
    }

    private void getLatestConfig()
    {
        // Record last time poller executed and guard against unregistered machines, e.g. no uuid set
        lastUpdateAttempt = new Date();
        LOG.debug("Attempting to acquire configuration at: " + lastUpdateAttempt.toString());
        LOG.debug("Attempting to acquire configuration for machine with UUID: " + ticketMachineUuid);
        if (ticketMachineUuid == null) return;

        try
        {
            TicketMachineConfig config = facade.getTicketMachineConfig(ticketMachineUuid);
            lastUpdateTime = lastUpdateAttempt;
            LOG.debug("Acquired configuration at: " + lastUpdateTime.toString());

            stationName = config.getStationName();
            stationZone = config.getStationZone();

            List<Station> stationList = config.getStationList();
            PricingSchedule pricingSchedule = config.getPricingSchedule();
        }
        catch (Exception ex)
        {
            LOG.error("Problem when attempting to download configuration", ex);
        }
    }

    public void shutdown()
    {
        LOG.error("Shutting down configuration poller");
        LOG.error("Shutting down scheduler");
        scheduler.shutdownNow();
    }
}
