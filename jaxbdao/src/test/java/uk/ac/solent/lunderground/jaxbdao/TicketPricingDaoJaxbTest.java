package uk.ac.solent.lunderground.jaxbdao;

import org.junit.Test;
import uk.ac.solent.lunderground.model.dto.PriceBand;
import uk.ac.solent.lunderground.model.dto.Rate;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class TicketPricingDaoJaxbTest
{
    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");
    private final static String FILE_PATH = TMP_DIR + File.separator + "pricing_test" + File.separator;
    private static final Double SOME_RATE = 1.0;
    private static final int ZERO_ELEMENTS = 0;
    private static final int TWO_ELEMENTS = 2;

    private final String testXmlUrl = "pricingTest.xml";

    @Test
    public void getPricingDetailsReturnsSetOffPeakPrice()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);
        dao.setOffPeakRate(SOME_RATE);

        assertThat(dao.getPricingDetails().getOffPeakRate(), equalTo(SOME_RATE));
    }

    @Test
    public void getPricingDetailsReturnsSetPeakPrice()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);
        dao.setPeakRate(SOME_RATE);

        assertThat(dao.getPricingDetails().getPeakRate(), equalTo(SOME_RATE));
    }

    @Test
    public void addPriceBandAddsPassedInPriceBand()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);
        PriceBand band = new PriceBand();
        dao.addPriceBand(band);

        assertThat(dao.getPricingDetails().getPriceBands(), contains(band));
    }

    @Test
    public void deleteAllPriceBandsRemovesAllThePriceBands()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);
        PriceBand band = new PriceBand();
        dao.addPriceBand(band);

        dao.deleteAllPriceBands();

        assertThat(dao.getPricingDetails().getPriceBands().size(), equalTo(ZERO_ELEMENTS));
    }

    @Test
    public void getRateBandReturnsOffPeakByDefault()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);

        assertThat(dao.getRateBand(new Date()), equalTo(Rate.OffPeak));
    }

    @Test
    public void getRateBandReturnsPeakWhenPassedInTimeAfterPriceBand()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);
        dao.addPriceBand(getBand(6, 30, Rate.Peak));

        Date date = getDate(6, 31);
        assertThat(dao.getRateBand(date), equalTo(Rate.Peak));
    }

    @Test
    public void getRateBandReturnsOffPeakWhenPassedInTimeAfterStartOfOffPeakPriceBand()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);
        dao.addPriceBand(getBand(6, 30, Rate.Peak));
        dao.addPriceBand(getBand(9, 30, Rate.OffPeak));

        Date date = getDate(9, 31);
        assertThat(dao.getRateBand(date), equalTo(Rate.OffPeak));
    }

    /**
     * Check that the stationTest.xml can be unmarshalled into a List of all Stations.
     */
    @Test
    public void loadUnmarshalsPricingBandsFromXmlFile()
    {
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);

        dao.load();

        List<?> list = dao.getPricingDetails()
                          .getPriceBands();
        assertThat(list.size(), equalTo(TWO_ELEMENTS));
    }

    @Test
    public void saveCreatesFileInSpecifiedPath()
    {
        String testPath = FILE_PATH + "testFile.xml";
        TicketPricingDaoJaxb dao = new TicketPricingDaoJaxb(testXmlUrl);

        dao.save(testPath);

        File file = new File(testPath);
        assertThat(file.exists(), equalTo(true));
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
