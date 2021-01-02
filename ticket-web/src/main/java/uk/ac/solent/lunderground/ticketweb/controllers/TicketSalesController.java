package uk.ac.solent.lunderground.ticketweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.TicketPricingDao;
import uk.ac.solent.lunderground.model.dto.Rate;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;
import uk.ac.solent.lunderground.ticketweb.WebClientObjectFactory;

import java.util.Date;

/**
 * Controller for the ticket sales page(s).
 */
@Controller
public class TicketSalesController
{
    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public String getSalesPage(final ModelMap map)
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();
        map.addAttribute("startStation", WebClientObjectFactory.getStationName());
        map.addAttribute("startZone", WebClientObjectFactory.getStationZone());
        map.addAttribute("stationList", WebClientObjectFactory.getStationList());

        return "ticket-sales";
    }

    @RequestMapping(value="/checkout", method=RequestMethod.POST)
    public String CheckoutPage(final ModelMap map, @RequestParam("stationName") final String destination)
    {
        Date issueDate = new Date();
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();
        StationDao stationDao = facade.getStationDao();
        TicketPricingDao ticketPricingDao = facade.getTicketPricingDao();

        Station startStation = stationDao.getStation(WebClientObjectFactory.getStationName());
        Station destinationStation = stationDao.getStation(destination);
        Rate rateBand = ticketPricingDao.getRateBand(issueDate);
        Double getJourneyPrice = ticketPricingDao.getJourneyPrice(startStation, destinationStation, issueDate);


//        Double getJourneyPrice(String stationName, String destination, Date issueDate);

//        Rate rateBand = facade.getRateBand(issueDate);
//        Double price = facade.getJourneyPrice(WebClientObjectFactory.getStationName(), destination, issueDate);

        map.addAttribute("startStation", WebClientObjectFactory.getStationName());
        map.addAttribute("startZone", WebClientObjectFactory.getStationZone());
        map.addAttribute("destStation", destinationStation.getName());
        map.addAttribute("destZone", destinationStation.getZone());
        map.addAttribute("timeStamp", issueDate);
        map.addAttribute("rateBand", rateBand);
//        map.addAttribute("price", price);

//                    <li>Display travel summary</li>
//                    <li>Display ticket price</li>
//                    <li></li>
//                    <li>Pay - valid card   - return to ticket-sales + display ticket</li>
//                    <li>Pay - invalid card - return to ticket-sales + display payment declined</li>
//                    <li>Cancel - return to ticket-sales</li>
        return "confirm-sale";
    }
}
