package uk.ac.solent;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap; // To be used later on
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
/**
 * This is the controller for the main landing page http://localhost/lunderground
 * This controller takes care of serving up the pages related to the various menu items
 */
public final class MainMenuController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    /**
     * Link the root URL for the project to the main landing page
     */
    public String mainPage()
    {
        return "index";
    }

    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    /**
     * Link to the page responsible for managing underground stations
     */
    public String manageStationsPage()
    {
        return "manage-stations";
    }
}
