package uk.ac.solent.lunderground.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@XmlRootElement(name = "PricingSchedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class PricingSchedule
{
    private final List<PriceBand> priceBands = new ArrayList<>();

    public Rate getRateBand(Date date)
    {
        PriceBand testBand = getTestBand(date);

        Rate rate = Rate.OffPeak;
        for (PriceBand priceBand: priceBands)
        {
            if (testBand.getTimeInMinutes() > priceBand.getTimeInMinutes())
            {
                rate = priceBand.getPricingRate();
                break;
            }
        }
        return rate;
    }

    private PriceBand getTestBand(Date date)
    {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        PriceBand testBand = new PriceBand();
        testBand.setHour(cal.get(Calendar.HOUR_OF_DAY));
        testBand.setMinute(cal.get(Calendar.MINUTE));
        return testBand;
    }

    public void addPriceBand(PriceBand band)
    {
        priceBands.add(band);
        Collections.sort(priceBands);
    }
}
