package uk.ac.solent.lunderground.clientrest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import uk.ac.solent.lunderground.model.dto.TicketMachine;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestServiceFacade implements TicketMachineFacade
{
    /**
     * Logger instance for the TicketMachineController implementation.
     */
    private static final Logger LOG = LogManager.getLogger(RestServiceFacade.class);

    static final String baseUrl = "http://localhost/lunderground/rest/v1/";

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
    }
}
