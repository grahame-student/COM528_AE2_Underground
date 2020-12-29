package uk.ac.solent.lunderground.model.dao;

import uk.ac.solent.lunderground.model.dto.PriceBand;

import java.util.List;

public interface ScheduleDao
{
    /**
     * Get a list of the currently configured price bands.
     * @return unordered list of the currently configured price bands
     */
    List<PriceBand> getSchedule();
    PriceBand getPriceBand(int hour, int minute);
    void addPriceBand(PriceBand newBand);
    void deletePriceBand(PriceBand delBand);
    void deleteAllPriceBands();
}
