package uk.ac.solent.lunderground.jaxbdao;

import org.junit.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StationDaoJaxbTest
{
    /**
     * Path to test XML file. Doesn't need to be super robust for testing purposes.
     */
    URL testXmlUrl = getClass().getClassLoader().getResource("test.xml");

    /**
     * Check that test.xml can be located. Testing the tests!
     */
    @Test
    public void LoadLocatesTestXmlFile()
    {
        StationDaoJaxb dao = new StationDaoJaxb(testXmlUrl.getPath());

        assertThat(dao.load(), equalTo(true));
    }
}
