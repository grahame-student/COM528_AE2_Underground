package uk.ac.solent.lunderground.jaxbdao;

import org.springframework.core.io.ClassPathResource;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.StationList;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        StationList stationList = getStationsFromStream(resourceFile);
        storeStationList(stationList);
    }

    public void load(String path)
    {
        File file = new File(path);

        try
        {
            InputStream stream = new FileInputStream(file);

            StationList stationList = getStationsFromStream(stream);
            storeStationList(stationList);
        }
        catch (FileNotFoundException e)
        {
            LOG.error("File at specified path does not exist: " + file.getAbsolutePath());
        }
    }

    private StationList getStationsFromStream(InputStream stream)
    {
        StationList stationList = null;
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(StationList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            stationList = (StationList) jaxbUnmarshaller.unmarshal(stream);
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

    /**
     * Save the configured station list to the directory specified
     * @param savePath Path to the directory to save the station list to
     */
    public void save(String savePath)
    {
        File file = new File(savePath);
        LOG.debug("Saving to: " + file.getAbsolutePath());

        if (file.exists())
        {
            LOG.debug("file already in specified location, removing it");
            file.delete();
        }

        saveToFile(file);
    }

    private void saveToFile(File file)
    {
        try
        {
            createSavePaths(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(StationList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StationList stationList = new StationList();
            stationList.setStationList(retrieveAll());
            jaxbMarshaller.marshal(stationList, file);
        }
        catch (JAXBException ex)
        {
            LOG.error("Problem marshalling to XML", ex);
            LOG.error("Error code: " + ex.getErrorCode());
            LOG.error(ex.getLocalizedMessage());
        }
    }

    private void createSavePaths(File file)
    {
        try
        {
            File parent = new File(file.getParent());
            parent.mkdirs();
        }
        catch (Exception ex)
        {
            LOG.error("Problem create parent directories", ex);
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
        LOG.debug("Not implemented, as not required by any client use cases");
    }

    @Override
    public void deleteAll()
    {
        LOG.debug("Removed all stations");
        stationMap.clear();
    }

    @Override
    public Station getStation(final String stationName)
    {
        LOG.debug("Not implemented, as not required by any client use cases");
        return null;
    }

    @Override
    public Station getStation(final Long stationId)
    {
        LOG.debug("Not implemented, as not required by any client use cases");
        return null;
    }

    @Override
    public void updateStation(final Station newDetails)
    {
        LOG.debug("Not implemented, as not required by any client use cases");
    }
}
