package uk.ac.solent.lunderground.ticketweb.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;
import uk.ac.solent.lunderground.ticketweb.WebClientObjectFactory;

/**
 * This is the controller for the main landing page http://localhost/ticket.
 */
@Controller
public class MainMenuController
{
    /**
     * Logger instance for the TicketMachineController implementation.
     */
    private static final Logger LOG = LogManager.getLogger(ConfigureTicketMachineController.class);

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String getMainPage(ModelMap map)
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();
        map.addAttribute("uuid", WebClientObjectFactory.getTicketMachineUuid());
        return "index";
    }
}
