package uk.ac.solent.lunderground.controllerweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.solent.lunderground.controllerweb.WebObjectFactory;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

/**
 * This is the controller for the main landing page http://localhost/lunderground.
 * The controller takes care of serving up the pages related to the various menu items
 */
@Controller
public final class MainMenuController
{
    /**
     * Reference to the London Underground Service Facade used to access the service available.
     */
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
     * @param map attributes map, used to inject data into the view
     * @return Return the .jsp to use for manage underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    public String getManageStationsPage(final ModelMap map)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return "manage-stations";
    }

    /**
     * Link to the controller responsible for adding a station to the database.
     *
     * @param map attributes map, used to inject data into the view
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/add", method = RequestMethod.POST)
    public ModelAndView getManageStationsAddPage(final ModelMap map)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return new ModelAndView("redirect:/manage-stations", map);
    }
}
