package uk.ac.solent.lunderground.controllerweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.ac.solent.lunderground.controllerweb.WebObjectFactory;
import uk.ac.solent.lunderground.model.dto.Station;
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
     * @return Return the .jsp to use for managing underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    public String getManageStationsPageNoParams(final ModelMap map)
    {
        return getManageStationsModelMapView(map);
    }

    /**
     * Serve the page responsible for managing underground stations.
     *
     * @param map attributes map, used to inject data into the view
     * @param newStation (optional) the name of the newly added station
     * @return Return the .jsp to use for managing underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET, params = {"newStation"})
    public String getManageStationsPageNewStation(final ModelMap map,
                                                  @RequestParam(name = "newStation") final String newStation)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("newStation", lunderGroundFacade.getStation(newStation));
        return getManageStationsModelMapView(map);
    }

    /**
     * Serve the page responsible for managing underground stations.
     *
     * @param map attributes map, used to inject data into the view
     * @param delStation (optional) the name of the newly deleted station
     * @return Return the .jsp to use for managing underground stations
     */
    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET, params = {"delStation"})
    public String getManageStationsPageDeleteStation(final ModelMap map,
                                                     @RequestParam(required = false, name = "delStation") final String delStation)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("delStation", delStation);
        return getManageStationsModelMapView(map);
    }

    /**
     * Handle the common part of serving up the manage stations URL.
     *
     * @param map attributes map, used to inject data into the view
     * @return Return the .jsp to use for managing underground stations
     */
    private String getManageStationsModelMapView(final ModelMap map)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        map.addAttribute("stations", this.lunderGroundFacade.getAllStations());
        return "manage-stations";
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
     *
     * @param redirectAttributes attributes to be added to the the redirect request
     * @param stationId The ID of the station to remove from the database
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/delete", method = RequestMethod.POST)
    public String getManageStationsDeletePage(final RedirectAttributes redirectAttributes,
                                              @RequestParam("id") final Long stationId)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        redirectAttributes.addAttribute("delStation", this.lunderGroundFacade.getStation(stationId).getName());
        this.lunderGroundFacade.deleteStation(stationId);
        return "redirect:/manage-stations";
    }

    /**
     * Serve the page responsible for getting the details of the station to be edited.
     *
     * @param redirectAttributes attributes to be added to the the redirect request
     * @param stationId The ID of the station to be edited
     * @return Return a redirect back to the page responsible form managing stations
     */
    @RequestMapping(value = "/manage-stations/edit", method = RequestMethod.POST)
    public String getManageStationsEditPage(final RedirectAttributes redirectAttributes,
                                            @RequestParam("id") final Long stationId)
    {
        this.lunderGroundFacade = WebObjectFactory.getServiceFacade();
        Station editStation = this.lunderGroundFacade.getStation(stationId);
        redirectAttributes.addAttribute("editStationId", editStation.getId());
        redirectAttributes.addAttribute("editStationName", editStation.getName());
        redirectAttributes.addAttribute("editStationZone", editStation.getZone());
        return "redirect:/manage-stations";
    }
}
