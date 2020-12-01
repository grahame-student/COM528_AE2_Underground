package uk.ac.solent.lunderground.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.ac.solent.lunderground.WebObjectFactory;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

/**
 * This is the controller for the main landing page http://localhost/lunderground.
 * The controller takes care of serving up the pages related to the various menu items
 */
@Controller
public final class MainMenuController
{
    private LundergroundServiceFacade lunderGroundFacade = null;

    /**
     * Link the root URL for the project to the main landing page.
     *
     * @return Return the .jsp to use for the root of the web app
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage()
    {
        return "index";
    }

    /**
     * Link to the page responsible for managing underground stations.
     *
     * @return Return the .jsp to use for manage underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    public String getManageStationsPage(ModelMap map)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return "manage-stations";
    }
}