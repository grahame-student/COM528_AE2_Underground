package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.PriceBand;
import uk.ac.solent.lunderground.model.dto.PricingDetails;
import uk.ac.solent.lunderground.model.dto.Rate;
import uk.ac.solent.lunderground.model.dto.Station;

import java.util.Date;

public interface TicketPricingDao
{
    void setOffPeakRate(Double newRate);
    void setPeakRate(Double newRate);

    Rate getRateBand(Date date);
    Double getPricePerZone(Date date);

    PricingDetails getPricingDetails();
    void setPricingDetails(PricingDetails pricingDetails);

    void addPriceBand(PriceBand newBand);
    void deleteAllPriceBands();
    Double getJourneyPrice(Station startStation, Station destinationStation, Date issueDate);
    Date getExpiryDate(Date issueDate);
}
