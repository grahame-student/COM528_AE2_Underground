package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class StationDaoJpa implements StationDao
{
    /**
     * Entity manager used for persistence.
     */
    private final EntityManager entityManager;

    /**
     * Public constructor for the StationDAO JPA implementation.
     * @param em Entity manager required for persistence
     */
    @NotNull
    public StationDaoJpa(final EntityManager em)
    {
        this.entityManager = em;
    }

    @Override
    public List<Station> retrieveAll()
    {
        TypedQuery<Station> q = entityManager.createQuery("SELECT s FROM Station s", Station.class);
        return q.getResultList();
    }

    @Override
    public void addStation(Station newStation)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(newStation);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll()
    {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Station ").executeUpdate();
        entityManager.getTransaction().commit();
    }
}
