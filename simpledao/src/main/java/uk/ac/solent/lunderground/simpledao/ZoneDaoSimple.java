package uk.ac.solent.lunderground.simpledao;

import uk.ac.solent.lunderground.model.dao.ZoneDao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ZoneDaoSimple implements ZoneDao
{
    @Override
    public List<Integer> retrieveAll()
    {
        // Create an int stream from 1 to 9 inclusive.
        // box from int to Integer (as List doesn't support primitive).
        // package into a list.
        return IntStream.rangeClosed(1, 9)
                        .boxed()
                        .collect(Collectors.toList());
    }
}
