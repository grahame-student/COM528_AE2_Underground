package uk.ac.solent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainMenuController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage()
    {
        // return the name of the file to be loaded "hello_world.jsp"
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPage()
    {
        // return the name of the file to be loaded "hello_world.jsp"
        return "test";
    }
}
