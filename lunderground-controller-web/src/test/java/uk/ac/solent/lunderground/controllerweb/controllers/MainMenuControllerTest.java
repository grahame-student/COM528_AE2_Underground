package uk.ac.solent.lunderground.controllerweb.controllers;

import org.junit.Test;

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
}
