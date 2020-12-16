package uk.ac.solent.lunderground.jpadao;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import uk.ac.solent.lunderground.model.dao.DaoFactory;
import uk.ac.solent.lunderground.model.dao.StationDao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.MockedStatic;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactoryJpaTest
{
    /**
     * Check that a StationDaoJpa instance is created when getStation() is called.
     */
    @Test
    public void getStationDaoReturnsStationDaoJpaInstance()
    {
        DaoFactory factory = new DaoFactoryJpa();

        StationDao stationDao = factory.getStationDao();

        assertThat(stationDao, instanceOf(StationDaoJpa.class));
    }

    /**
     * Check that runtime exception is raised when the persistence manage throws an exception.
     */
    @Test
    @Ignore("Test disabled as mocking the persistence manager is causing additional tests to fail.\n"
            + "This is because the method under test creates a singleton and resetting the internal\n"
            + "state of the class has proven itself to be unexpectedly tricky.\n"
            + "This test DOES pass when run in isolation and is left here as a sanity check that\n"
            + "can be run on demand when required.")
    public void getStationDaoThrowsRuntimeExceptionWhenPersistenceManagerThrowsException()
    {
        DaoFactory factory = new DaoFactoryJpa();

        try (MockedStatic<Persistence> persistence = Mockito.mockStatic(Persistence.class))
        {
            EntityManagerFactory mockEmFactory = mock(EntityManagerFactory.class);
            when(mockEmFactory.createEntityManager()).thenThrow(IllegalStateException.class);
            persistence.when(() -> Persistence.createEntityManagerFactory(anyString()))
                       .thenReturn(mockEmFactory);

            Exception exception = assertThrows(RuntimeException.class, factory::getStationDao);

            assertThat(exception, instanceOf(RuntimeException.class));
        }
    }
}
