package uk.ac.solent.lunderground.controllerweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * Serve the main landing page for the root URL.
     *
     * @return Return the .jsp to use for the root of the web app
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMainPage()
    {
        return "index";
    }

    /**
     * Serve the page responsible for managing underground stations.
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
     * Serve the page responsible for adding a station to the database.
     *
     * @param name The name of the station to be added
     * @param zone The zone that the station is in
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/add", method = RequestMethod.POST)
    public ModelAndView getManageStationsAddPage(@RequestParam("stationName") final String name,
                                                 @RequestParam("zoneNumber") final Integer zone)
    {
        ModelMap map = new ModelMap();
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        this.lunderGroundFacade.addStation(name, zone);
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return new ModelAndView("redirect:/manage-stations", map);
    }

    /**
     * Serve the page responsible for removing a station from the database.
     * @param stationId The ID of the station to remove from the database
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/delete", method = RequestMethod.POST)
    public ModelAndView getManageStationsDeletePage(@RequestParam("id") final Integer stationId)
    {
        ModelMap map = new ModelMap();
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        this.lunderGroundFacade.deleteStation(stationId);
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return new ModelAndView("redirect:/manage-stations", map);
    }
}
