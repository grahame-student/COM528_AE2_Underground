package uk.ac.solent.lunderground.controllerweb.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.solent.lunderground.controllerweb.WebObjectFactory;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

@RestController
public class RestEndPointController
{
    /**
     * Logger instance for the StationDaoJaxb implementation.
     */
    private static final Logger LOG = LogManager.getLogger(RestEndPointController.class);

    /**
     *
     */
    private LundergroundServiceFacade lunderGroundFacade = null;

    /**
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/rest/v1/ticketMachineConfig/{uuid}", method = RequestMethod.GET)
    public TicketMachineConfig getTicketMachineConfig(@PathVariable String uuid)
    {
        LOG.info("Requested ticket machine config for machine with uuid: " + uuid);
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        TicketMachineConfig config = new TicketMachineConfig();
        config.setStationList(this.lunderGroundFacade.getAllStations());

        return config;
    }
}
