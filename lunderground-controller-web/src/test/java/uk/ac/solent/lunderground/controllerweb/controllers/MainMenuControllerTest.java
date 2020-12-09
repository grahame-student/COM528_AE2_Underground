package uk.ac.solent.lunderground.controllerweb.controllers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.MockedStatic;

import org.springframework.ui.ModelMap;

import org.springframework.web.servlet.ModelAndView;
import uk.ac.solent.lunderground.controllerweb.WebObjectFactory;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;
import uk.ac.solent.lunderground.service.LundergroundFacade;

import java.util.ArrayList;
import java.util.List;

public class MainMenuControllerTest
{
    /**
     * Zone number used for testing, specific value is not important.
     */
    private static final Integer SOME_ZONE = 4;
    /**
     * Station name used for testing, specific value is not important.
     */
    private static final String SOME_STATION = "some station";
    /**
     * ID used for testing, specific value is not important.
     */
    private static final Integer SOME_ID = 123;

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
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelMap mockModelMap = mock(ModelMap.class);
            controller.getManageStationsPage(mockModelMap);

            verify(mockModelMap).addAttribute(eq("stations"), anyList());
        }
    }

    /**
     * Check that getManageStationsPage gets a list of all stations from the facade.
     */
    @Test
    public void getManageStationsPageGetsStationListFromFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelMap mockModelMap = mock(ModelMap.class);
            controller.getManageStationsPage(mockModelMap);

            verify(mockFacade).getAllStations();
        }
    }

    /**
     * Check that a stations attribute has been set from the Facade's getAllStations method.
     */
    @Test
    public void getManageStationsPageSetStationsAttributeFromFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            List<Station> stationsList = new ArrayList<>();
            when(mockFacade.getAllStations()).thenReturn(stationsList);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelMap map = new ModelMap();
            controller.getManageStationsPage(map);

            Object stations = map.getAttribute("stations");
            assertThat(stations, equalTo(stationsList));
        }
    }

    /**
     * Check that getManageStationsAddPage adds a station using the facade.
     */
    @Test
    public void getManageStationsAddPageAddsStationUsingFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            controller.getManageStationsAddPage(SOME_STATION, SOME_ZONE);

            verify(mockFacade).addStation(anyString(), anyInt());
        }
    }

    /**
     * Check that we're returning the correct type of object.
     */
    @Test
    public void getManageStationsAddPageReturnsModelAndViewInstance()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            Object result = controller.getManageStationsAddPage(SOME_STATION, SOME_ZONE);

            assertThat(result, instanceOf(ModelAndView.class));
        }
    }

    /**
     * Check that we're asking for a redirect to the page responsible for managing stations.
     */
    @Test
    public void getManageStationsAddPageRedirectsToManageStationsPage()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsAddPage(SOME_STATION, SOME_ZONE);

            assertThat(result.getViewName(), equalTo("redirect:/manage-stations"));
        }
    }

    /**
     * Check that a stations attribute has been set in the ModelMap.
     */
    @Test
    public void getManageStationsAddPageSetStationsAttribute()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsAddPage(SOME_STATION, SOME_ZONE);

            Object stations = result.getModelMap().getAttribute("stations");
            assertThat(stations, notNullValue());
        }
    }

    /**
     * Check that a stations attribute has been set from the Facade's getAllStations method.
     */
    @Test
    public void getManageStationsAddPageSetStationsAttributeFromFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            List<Station> stationsList = new ArrayList<>();
            when(mockFacade.getAllStations()).thenReturn(stationsList);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsAddPage(SOME_STATION, SOME_ZONE);

            Object stations = result.getModelMap().getAttribute("stations");
            assertThat(stations, equalTo(stationsList));
        }
    }

    /**
     * Check that getManageStationsDeletePage adds a station using the facade.
     */
    @Test
    public void getManageStationsDeletePageDeletesStationUsingFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            controller.getManageStationsDeletePage(SOME_ID);

            verify(mockFacade).deleteStation(anyInt());
        }
    }

    /**
     * Check that we're returning the correct type of object.
     */
    @Test
    public void getManageStationsDeletePageReturnsModelAndViewInstance()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsDeletePage(SOME_ID);

            assertThat(result, instanceOf(ModelAndView.class));
        }
    }

    /**
     * Check that we're asking for a redirect to the page responsible for managing stations.
     */
    @Test
    public void getManageStationsDeletePageRedirectsToManageStationsPage()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsDeletePage(SOME_ID);

            assertThat(result.getViewName(), equalTo("redirect:/manage-stations"));
        }
    }

    /**
     * Check that a stations attribute has been set in the ModelMap.
     */
    @Test
    public void getManageStationsDeletePageSetStationsAttribute()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsDeletePage(SOME_ID);

            Object stations = result.getModelMap().getAttribute("stations");
            assertThat(stations, notNullValue());
        }
    }

    /**
     * Check that a stations attribute has been set from the Facade's getAllStations method.
     */
    @Test
    public void getManageStationsDeletePageSetStationsAttributeFromFacade()
    {
        try (MockedStatic<WebObjectFactory> factory = Mockito.mockStatic(WebObjectFactory.class))
        {
            LundergroundServiceFacade mockFacade = mock(LundergroundFacade.class);
            List<Station> stationsList = new ArrayList<>();
            when(mockFacade.getAllStations()).thenReturn(stationsList);
            factory.when(WebObjectFactory::getServiceFacade)
                   .thenReturn(mockFacade);

            MainMenuController controller = new MainMenuController();
            ModelAndView result = controller.getManageStationsDeletePage(SOME_ID);

            Object stations = result.getModelMap().getAttribute("stations");
            assertThat(stations, equalTo(stationsList));
        }
    }
}
