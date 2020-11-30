package uk.ac.solent;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap; // To be used later on
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the controller for the main landing page http://localhost/lunderground
 * This controller takes care of serving up the pages related to the various menu items
 */
@Controller
public final class MainMenuController
{
    /**
     * Link the root URL for the project to the main landing page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage()
    {
        return "index";
    }

    /**
     * Link to the page responsible for managing underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    public String manageStationsPage()
    {
        return "manage-stations";
    }
}
