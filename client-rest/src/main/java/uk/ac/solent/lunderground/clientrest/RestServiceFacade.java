package uk.ac.solent.lunderground.clientrest;

import org.springframework.web.client.RestTemplate;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.util.HashMap;
import java.util.Map;

public class RestServiceFacade implements TicketMachineFacade
{
    String baseUrl = "http://localhost/lunderground/rest/v1/";

    @Override
    public TicketMachineConfig getTicketMachineConfig(String uuid)
    {
        final String uri = baseUrl + "ticketMachineConfig/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        return template.getForObject(uri, TicketMachineConfig.class, params);
    }
}
