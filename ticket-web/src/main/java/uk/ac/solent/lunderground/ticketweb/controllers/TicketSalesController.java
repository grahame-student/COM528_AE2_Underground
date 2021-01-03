package uk.ac.solent.lunderground.ticketweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.solent.lunderground.model.dto.Ticket;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;
import uk.ac.solent.lunderground.ticketweb.WebClientObjectFactory;

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
    public String checkoutPage(final ModelMap map, @RequestParam("stationName") final String destination)
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();
        Ticket ticket = facade.getTicket(WebClientObjectFactory.getStationName(), destination);

        map.addAttribute("ticket", ticket);

        return "confirm-sale";
    }

    @RequestMapping(value = "/buyTicket", method = RequestMethod.POST)
    public String buyTicket(final ModelMap map,
                            @RequestParam("cardValid") final boolean cardValid,
                            @ModelAttribute("ticket") final Ticket ticket)
    {
        TicketMachineFacade facade = WebClientObjectFactory.getServiceFacade();

        map.addAttribute("xmlTicket", "Payment Card Not Valid - Sale Cancelled");
        if (cardValid)
        {
            // We only add a validation code if the payment card is valid
            map.addAttribute("xmlTicket", facade.encodeTicket(ticket));
        }
        map.addAttribute("ticket", ticket);
        return "issued-ticket";
    }
}
