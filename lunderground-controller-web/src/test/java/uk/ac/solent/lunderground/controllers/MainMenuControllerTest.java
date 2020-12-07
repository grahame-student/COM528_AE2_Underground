package uk.ac.solent.lunderground.controllers;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import uk.ac.solent.lunderground.LundergroundFacade;
import uk.ac.solent.lunderground.WebObjectFactory;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainMenuControllerTest
{
    /**
     * Check that getMainPage returns the name of the intended landing page.
     * Not the most useful test as the method attributes are not exercised
     */
    @Test
    public void getMainPageReturnsIndex()
    {
        MainMenuController controller = new MainMenuController();

        assertThat(controller.getMainPage(), equalTo("index"));
    }

    /**
     * Check that getManageStationsPage returns the name of the manage stations page.
     * Not the most useful test as the method attributes are not exercised
     */
    @Test
    public void getManageStationsPageReturnsManageStations()
    {
        MainMenuController controller = new MainMenuController();

        assertThat(controller.getManageStationsPage(new ModelMap()), equalTo("manage-stations"));
    }

    /**
     * Check that getManageStationsPage adds a station list to the ModelMap.
     */
    @Test
    public void getManageStationsPageAddStationListToModelMap()
    {
        LundergroundServiceFacade facade = WebObjectFactory.getServiceFacade();

        MainMenuController controller = new MainMenuController();

    }
}
