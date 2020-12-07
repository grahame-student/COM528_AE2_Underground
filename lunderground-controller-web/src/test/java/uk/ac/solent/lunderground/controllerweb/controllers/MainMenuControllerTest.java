package uk.ac.solent.lunderground.controllerweb.controllers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import org.springframework.ui.ModelMap;

import uk.ac.solent.lunderground.service.LundergroundFacade;
import uk.ac.solent.lunderground.controllerweb.WebObjectFactory;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

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
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade).thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelMap mockModelMap = mock(ModelMap.class);
            controller.getManageStationsPage(mockModelMap);

            verify(mockModelMap).addAttribute(eq("stations"), anyList());
        }
    }

    /**
     * Check that getManageStationsPage adds a station list to the ModelMap.
     */
    @Test
    public void getManageStationsPageGetsStationListFromFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade).thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelMap mockModelMap = mock(ModelMap.class);
            controller.getManageStationsPage(mockModelMap);

            verify(mockFacade).getAllStations();
        }
    }
}
