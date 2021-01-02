package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.PriceBand;
import uk.ac.solent.lunderground.model.dto.Rate;

import java.util.Date;

public interface TicketPricingDao
{
    Rate getRate(Date date);
    Double getPricePerZone(Date date);

    void addPriceBand(PriceBand newBand);
    void deleteAllPriceBands();
}
