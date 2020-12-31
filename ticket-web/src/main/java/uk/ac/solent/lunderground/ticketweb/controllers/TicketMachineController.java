package uk.ac.solent.lunderground.ticketweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;
import uk.ac.solent.lunderground.ticketweb.WebClientObjectFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the controller for the main landing page http://localhost/lunderground.
 * The controller takes care of serving up the pages related to the various menu items
 */
@Controller
public class TicketMachineController
{
    /**
     * Logger instance for the TicketMachineController implementation.
     */
    private static final Logger LOG = LogManager.getLogger(TicketMachineController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage(ModelMap map)
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();
        map.addAttribute("uuid", WebClientObjectFactory.getTicketMachineUuid());
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String GetRegistrationPage()
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();
        // If we've got here it is because the ticket machine has not been previously registered.
        // Therefore we
        // - assign a uuid
        // - add the machine to the registry
        // - redirect to the configuration page for additional configuration to take place
        WebClientObjectFactory.setTicketMachineUuid();
        facade.addTicketMachine(WebClientObjectFactory.getTicketMachineUuid());

        return "redirect:/configure";
    }

    @RequestMapping(value = "/configure", method = RequestMethod.GET)
    public String GetConfigurationPage(ModelMap map)
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();

        // Added for initial testing
        map.addAttribute("machineConfig", facade.getTicketMachineConfig(WebClientObjectFactory.getTicketMachineUuid()));

        return "configureMachine";
    }

    // TODO: Add customer page
}
