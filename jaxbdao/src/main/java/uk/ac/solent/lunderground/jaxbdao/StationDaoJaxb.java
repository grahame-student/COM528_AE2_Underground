package uk.ac.solent.lunderground.jaxbdao;

import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.StationList;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

    /**
     * The encoding to use when decoding URLs.
     */
    private static final String URL_ENCODING = StandardCharsets.UTF_8.toString();

    /**
     * Path to use when marshalling to XML / unmarshalling from XML.
     */
    private final String path;

    /**
     * Tree map of the various stations.
     * The station name is the key and the station instance is the value
     */
    private final TreeMap<String, Station> stationMap = new TreeMap<>();

    /**
     * Constructor for the JAXB StationDao implementation.
     *
     * @param resourceURL URL pointing to an XML file that should be marshalled to or
     *                    unmarshalled from.
     */
    public StationDaoJaxb(@NotNull final URL resourceURL)
    {
        String tmpPath = resourceURL.getPath();
        try
        {
            // Decode the URL in case there are any escaped chars in it.
            // Escaped chars can cause all sorts of issues locating and accessing resources.
            tmpPath = URLDecoder.decode(resourceURL.getPath(), URL_ENCODING);
        }
        catch (UnsupportedEncodingException ex)
        {
            LOG.error("Problem whilst decoding Stations URL");
            LOG.error(URL_ENCODING + " is not a supported URL encoding");
            LOG.debug("Unable to decode " + resourceURL);
            LOG.debug("If the supplied URL contains escaped chars this may cause further issues");
        }
        path = tmpPath;
    }

    /**
     * Load a list of stations from XML.
     */
    public void load()
    {
        File file = new File(path);
        if (!file.exists())
        {
            LOG.debug("No file found at: " + path);
        }
        else
        {
            StationList stationList = getStationsFromFile(file);
            storeStationList(stationList);
        }
    }

    private StationList getStationsFromFile(final File file)
    {
        StationList stationList = null;
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(StationList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            stationList = (StationList) jaxbUnmarshaller.unmarshal(file);
        }
        catch (JAXBException ex)
        {
            LOG.error("Unable to unmarshall the Stations list");
            LOG.error("Error code: " + ex.getErrorCode());
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

    public void save()
    {
        File file = new File(path);
        saveStationsToFile(file);
    }

    private void saveStationsToFile(final File file)
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(StationList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StationList stationList = new StationList();
            stationList.setStationList(this.retrieveAll());
            jaxbMarshaller.marshal(stationList, file);
        }
        catch (JAXBException e)
        {
            LOG.error("Unable to marshall station list to XML");
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
