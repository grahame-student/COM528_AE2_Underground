package uk.ac.solent;

import org.springframework.stereotype.Controller;
// import org.springframework.ui.ModelMap; // To be used later on
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public final class MainMenuController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final String mainPage()
    {
        return "index";
    }

    @RequestMapping(value = "/manage-stations", method = RequestMethod.GET)
    public final String manageStationsPage()
    {
        return "manage-stations";
    }
}
