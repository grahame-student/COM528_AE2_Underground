package uk.ac.solent.lunderground;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public final class StationDaoJpa implements StationDao
{
    private final EntityManager entityManager;

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
}
