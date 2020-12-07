package uk.ac.solent.lunderground;

import org.junit.Test;
import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;
import uk.ac.solent.lunderground.model.service.ServiceObjectFactory;
import uk.ac.solent.lunderground.service.LundergroundFacade;
import uk.ac.solent.lunderground.service.ServiceObjectFactoryJpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class ServiceObjectFactoryTest
{
    /**
     * Test that a StationDaoJpa instance is created when getStation() is called.
     */
    @Test
    public void getLundergroundFacadeReturnsInstanceOfLundergroundFacade()
    {
        ServiceObjectFactory factory = new ServiceObjectFactoryJpa();

        LundergroundServiceFacade facade = factory.getLundergroundFacade();

        assertThat(facade, instanceOf(LundergroundFacade.class));
    }
}
