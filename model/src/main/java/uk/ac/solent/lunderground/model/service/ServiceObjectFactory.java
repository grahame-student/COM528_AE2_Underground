package uk.ac.solent.lunderground.model.service;

public interface ServiceObjectFactory
{
    /**
     * Get a service facade that can be used to access the London underground services.
     *
     * @return Service facade that implements the LundergroundServiceFacade interface
     */
    LundergroundServiceFacade getLundergroundFacade();
}
