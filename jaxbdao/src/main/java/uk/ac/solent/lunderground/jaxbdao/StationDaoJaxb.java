package uk.ac.solent.lunderground.jaxbdao;

import org.springframework.core.io.ClassPathResource;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.StationList;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StationDaoJaxb implements StationDao
{
    /**
     * Logger instance for the StationDaoJaxb implementation.
     */
    private static final Logger LOG = LogManager.getLogger(StationDaoJaxb.class);

    private final InputStream resourceFile;

    /**
     * Tree map of the various stations.
     * The station name is the key and the station instance is the value
     */
    private final TreeMap<String, Station> stationMap = new TreeMap<>();

    /**
     * Constructor for the JAXB StationDao implementation.
     *
     * @param resourceName URL pointing to an XML file that should be marshalled to or
     *                    unmarshalled from.
     */
    public StationDaoJaxb(@NotNull final String resourceName)
    {
        InputStream tmpFile = null;
        try
        {
            tmpFile = new ClassPathResource(resourceName).getInputStream();
        }
        catch (IOException e)
        {
            LOG.error("unable to find " + resourceName + " in the class path");
        }
        resourceFile = tmpFile;
    }

    /**
     * Load a list of stations from XML.
     */
    public void load()
    {
        StationList stationList = getStationsFromFile();
        storeStationList(stationList);
    }

    private StationList getStationsFromFile()
    {
        StationList stationList = null;
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(StationList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            stationList = (StationList) jaxbUnmarshaller.unmarshal(resourceFile);
        }
        catch (JAXBException ex)
        {
            LOG.error("Unable to unmarshall the Stations list");
            LOG.error("Error code: " + ex.getErrorCode());
            LOG.error(ex.getLocalizedMessage());
        }
        return stationList;
    }

    private void storeStationList(final StationList stationList)
    {
        stationMap.clear();
        if (stationList != null)
        {
            for (Station station : stationList.getStationList())
            {
                stationMap.put(station.getName(), station);
            }
        }
        else
        {
            LOG.debug("Station list is null, unable to store.");
        }
    }

    @Override
    public synchronized List<Station> retrieveAll()
    {
        return new ArrayList<>(stationMap.values());
    }

    @Override
    public synchronized void addStation(final Station newStation)
    {
        stationMap.put(newStation.getName(), newStation);
    }

    @Override
    public void deleteStation(final long stationId)
    {
        LOG.debug("Not implemented, as not required by any use cases");
    }

    @Override
    public void deleteAll()
    {
        LOG.debug("Not implemented, as not required by any use cases");
    }

    @Override
    public Station getStation(final String stationName)
    {
        LOG.debug("Not implemented, as not required by any use cases");
        return null;
    }

    @Override
    public Station getStation(final Long stationId)
    {
        LOG.debug("Not implemented, as not required by any use cases");
        return null;
    }

    @Override
    public void updateStation(final Station newDetails)
    {
        LOG.debug("Not implemented, as not required by any use cases");
    }
}
