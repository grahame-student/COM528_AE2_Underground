package uk.ac.solent.lunderground.controllerweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.ac.solent.lunderground.controllerweb.WebObjectFactory;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

@Controller
public class ManageStationsController
{
    /**
     * Reference to the London Underground Service Facade used to access the service available.
     */
    private LundergroundServiceFacade lunderGroundFacade = null;

    /**
     * Serve the page responsible for managing underground stations.
     *
     * @param map attributes map, used to inject data into the view
     * @param newStation (optional) the name of the newly added station
     * @return Return the .jsp to use for manage underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    public String getManageStationsPage(final ModelMap map,
                                        @RequestParam(required = false, name="newStation") final String newStation)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        addAttribute(map, lunderGroundFacade, newStation);
        return "manage-stations";
    }

    private static void addAttribute(final ModelMap map, final LundergroundServiceFacade facade,
                                     final String newStation)
    {
        if (newStation != null)
        {
            map.addAttribute("newStation", facade.getStation(newStation));
        }
    }

    /**
     * Serve the page responsible for adding a station to the database.
     *
     * @param redirectAttributes attributes to be added to the the redirect request
     * @param name The name of the station to be added
     * @param zone The zone that the station is in
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/add", method = RequestMethod.POST)
    public String getManageStationsAddPage(final RedirectAttributes redirectAttributes,
                                           @RequestParam("stationName") final String name,
                                           @RequestParam("zoneNumber") final Integer zone)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        this.lunderGroundFacade.addStation(name, zone);
        redirectAttributes.addAttribute("newStation", name);
        return "redirect:/manage-stations";
    }

    /**
     * Serve the page responsible for removing a station from the database.
     * @param stationId The ID of the station to remove from the database
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/delete", method = RequestMethod.POST)
    public ModelAndView getManageStationsDeletePage(@RequestParam("id") final Long stationId)
    {
        ModelMap map = new ModelMap();
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        this.lunderGroundFacade.deleteStation(stationId);
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return new ModelAndView("redirect:/manage-stations", map);
    }
}
