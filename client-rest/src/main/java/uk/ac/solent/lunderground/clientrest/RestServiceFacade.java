package uk.ac.solent.lunderground.clientrest;

import org.springframework.web.client.RestTemplate;
import uk.ac.solent.lunderground.model.dto.Rate;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.TicketMachine;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestServiceFacade implements TicketMachineFacade
{
    private Runnable configChangedCallback = null;
    /**
     * Logger instance for the TicketMachineController implementation.
     */
    private static final Logger LOG = LogManager.getLogger(RestServiceFacade.class);

    private final String baseUrl;

    public RestServiceFacade(String baseRestUrl)
    {
        this.baseUrl = baseRestUrl;
    }

    @Override
    public void ticketMachineConfigChanged(Runnable callback)
    {
        configChangedCallback = callback;
    }

    @Override
    public TicketMachineConfig getTicketMachineConfig(String uuid)
    {
        final String uri = baseUrl + "ticketMachineConfig/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        // We use a get as we are retrieving information
        return template.getForObject(uri, TicketMachineConfig.class, params);
    }

    @Override
    public void addTicketMachine(String uuid)
    {
        final String uri = baseUrl + "ticketMachine";

        RestTemplate template = new RestTemplate();

        TicketMachine newMachine = new TicketMachine();
        newMachine.setUuid(uuid);

        TicketMachine result = template.postForObject(uri, newMachine, TicketMachine.class);
        LOG.info("added new ticket machine: " + result);
        configUpdated();
    }

    @Override
    public void updateTicketMachine(String uuid, String stationName)
    {
        final String uri = baseUrl + "ticketMachine/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        TicketMachine tm = getTicketMachine(uuid);
        Station station = getStation(stationName);
        tm.setStation(station);

        // We use a put as we're updating an existing station
        template.put(uri, tm, params);
        configUpdated();
    }

    @Override
    public Double getJourneyPrice(String startStation, String destinationStation, Date issueDate)
    {
        Station start = getStation(startStation);
        Station dest = getStation(destinationStation);
        Integer zonesTravelled = Math.abs(start.getZone() - dest.getZone());
        Double basePrice = getBasePrice(zonesTravelled, issueDate);

        return basePrice * zonesTravelled;
    }

    @Override
    public Rate getRateBand(Date issueDate)
    {
        // TODO:
        return null;
    }

    private Double getBasePrice(Integer zonesTravelled, Date issueDate)
    {
        final String uri = baseUrl + "price/{zones}/{issueDate}";

        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("zones", zonesTravelled.toString());
        params.put("issueDate", issueDate.toString());

        return 0.0;
        // return template.getForObject(uri, Double.class, params);
    }

    private TicketMachine getTicketMachine(String uuid)
    {
        final String uri = baseUrl + "ticketMachine/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        // We use a get as we are retrieving information
        return template.getForObject(uri, TicketMachine.class, params);
    }

    private Station getStation(String stationName)
    {
        final String uri = baseUrl + "station/{name}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("name", stationName);

        // We use a get as we are retrieving information
        return template.getForObject(uri, Station.class, params);
    }

    /**
     * Execute the registered callback.
     */
    private void configUpdated()
    {
        if (configChangedCallback != null)
        {
            configChangedCallback.run();
        }
    }
}
