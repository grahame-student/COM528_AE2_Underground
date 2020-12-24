import org.junit.Test;
import uk.ac.solent.lunderground.model.dao.ZoneDao;
import uk.ac.solent.lunderground.simpledao.ZoneDaoSimple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ZoneDaoSimpleTest
{
    /**
     * Check that retrieveAll returns a list of 9 items
     */
    @Test
    public void RetrieveAllReturnsListOfIntegersFromOneToNine()
    {
        ZoneDao zoneDao = new ZoneDaoSimple();

        List<Integer> expectedList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        assertThat(zoneDao.retrieveAll(), equalTo(expectedList));
    }
}
