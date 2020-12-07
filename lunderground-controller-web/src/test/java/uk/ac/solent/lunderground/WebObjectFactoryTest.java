package uk.ac.solent.lunderground;

import org.junit.Test;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class WebObjectFactoryTest
{
    @Test
    public void getServiceFacadeReturnsInstanceOfLundergroundFacade()
    {
        LundergroundServiceFacade facade = WebObjectFactory.getServiceFacade();

        assertThat(facade, instanceOf(LundergroundFacade.class));
    }

    @Test
    public void getServiceFacadeReturnsSameInstanceWhenCalledMultipleTimes()
    {
        LundergroundServiceFacade facade1 = WebObjectFactory.getServiceFacade();

        LundergroundServiceFacade facade2 = WebObjectFactory.getServiceFacade();

        Boolean same_instance = (facade1 == facade2);
        assertThat(same_instance, equalTo(true));
    }
}
