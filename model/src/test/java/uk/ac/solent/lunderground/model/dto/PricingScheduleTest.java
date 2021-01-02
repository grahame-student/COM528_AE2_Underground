package uk.ac.solent.lunderground.model.dto;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PricingScheduleTest
{
    @Test
    public void getRateBandReturnsOffPeakByDefault()
    {
        PricingSchedule schedule = new PricingSchedule();

        assertThat(schedule.getRateBand(new Date()), equalTo(Rate.OffPeak));
    }

    @Test
    public void getRateBandReturnsPeakWhenPassedInTimeAfterPriceBand()
    {
        PricingSchedule schedule = new PricingSchedule();
        schedule.addPriceBand(getBand(6, 30, Rate.Peak));

        Date date = getDate(6, 31);
        assertThat(schedule.getRateBand(date), equalTo(Rate.Peak));
    }

    @Test
    public void getRateBandReturnsOffPeakWhenPassedInTimeAfterStartOfOffPeakPriceBand()
    {
        PricingSchedule schedule = new PricingSchedule();
        schedule.addPriceBand(getBand(6, 30, Rate.Peak));
        schedule.addPriceBand(getBand(9, 30, Rate.OffPeak));

        Date date = getDate(9, 31);
        assertThat(schedule.getRateBand(date), equalTo(Rate.OffPeak));
    }

    // Helper methods to simplify test cases
    private Date getDate(int hour, int minute)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.set(2020, Calendar.DECEMBER, 3, hour, minute, 33);
        return calendar.getTime();
    }

    private PriceBand getBand(int hour, int minute, Rate rate)
    {
        PriceBand tmpBand = new PriceBand();
        tmpBand.setHour(hour);
        tmpBand.setMinute(minute);
        tmpBand.setPricingRate(rate);
        return tmpBand;
    }
}
