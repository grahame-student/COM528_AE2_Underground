package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.service.LundergroundServiceFacade;
import uk.ac.solent.lunderground.model.service.ServiceObjectFactory;

public final class WebObjectFactory
{
    private static LundergroundServiceFacade lundergroundFacade = null;

    private WebObjectFactory()
    {
    }

    public static LundergroundServiceFacade getServiceFacade()
    {
        if (lundergroundFacade == null)
        {
            synchronized (WebObjectFactory.class)
            {
                if (lundergroundFacade == null)
                {
                    ServiceObjectFactory clientObjectFactory = new ServiceObjectFactoryJpa();
                    lundergroundFacade = clientObjectFactory.getLundergroundFacade();
                }
            }
        }
        return lundergroundFacade;
    }
}
