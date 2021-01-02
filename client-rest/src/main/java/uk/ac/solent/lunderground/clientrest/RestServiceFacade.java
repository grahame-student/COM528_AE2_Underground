package uk.ac.solent.lunderground.clientrest;

import org.springframework.web.client.RestTemplate;
import uk.ac.solent.lunderground.jaxbdao.StationDaoJaxb;
import uk.ac.solent.lunderground.jaxbdao.TicketPricingDaoJaxb;
import uk.ac.solent.lunderground.model.dao.StationDao;
import uk.ac.solent.lunderground.model.dao.TicketPricingDao;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.TicketMachine;
import uk.ac.solent.lunderground.model.dto.TicketMachineConfig;
import uk.ac.solent.lunderground.model.service.TicketMachineFacade;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RestServiceFacade implements TicketMachineFacade
{
    private final static String TMP_DIR = System.getProperty("java.io.tmpdir");
    private final static String STATION_PATH = TMP_DIR + File.separator
                                            + "ticket_machine_client" + File.separator + "clientStationList.xml";
    private final static String PRICING_PATH = TMP_DIR + File.separator
                                               + "ticket_machine_client" + File.separator + "clientPricingDetails.xml";

    private static StationDao stationDAO = null;
    private static TicketPricingDaoJaxb ticketPricingDao = null;

    private Runnable configChangedCallback = null;
    /**
     * Logger instance for the TicketMachineController implementation.
     */
    private static final Logger LOG = LogManager.getLogger(RestServiceFacade.class);

    private final String baseUrl;

    public RestServiceFacade(String baseRestUrl)
    {
        this.baseUrl = baseRestUrl;
    }

    @Override
    public void ticketMachineConfigChanged(Runnable callback)
    {
        configChangedCallback = callback;
    }

    @Override
    public TicketMachineConfig getTicketMachineConfig(String uuid)
    {
        final String uri = baseUrl + "ticketMachineConfig/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        // We use a get as we are retrieving information
        return template.getForObject(uri, TicketMachineConfig.class, params);
    }

    @Override
    public void addTicketMachine(String uuid)
    {
        final String uri = baseUrl + "ticketMachine";

        RestTemplate template = new RestTemplate();

        TicketMachine newMachine = new TicketMachine();
        newMachine.setUuid(uuid);

        TicketMachine result = template.postForObject(uri, newMachine, TicketMachine.class);
        LOG.info("added new ticket machine: " + result);
        configUpdated();
    }

    @Override
    public void updateTicketMachine(String uuid, String stationName)
    {
        final String uri = baseUrl + "ticketMachine/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        TicketMachine tm = getTicketMachine(uuid);
        Station station = getStation(stationName);
        tm.setStation(station);

        // We use a put as we're updating an existing station
        template.put(uri, tm, params);
        configUpdated();
    }

    private TicketMachine getTicketMachine(String uuid)
    {
        final String uri = baseUrl + "ticketMachine/{uuid}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("uuid", uuid);

        // We use a get as we are retrieving information
        return template.getForObject(uri, TicketMachine.class, params);
    }

    private Station getStation(String stationName)
    {
        final String uri = baseUrl + "station/{name}";

        RestTemplate template = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("name", stationName);

        // We use a get as we are retrieving information
        return template.getForObject(uri, Station.class, params);
    }

    /**
     * Execute the registered callback.
     */
    private void configUpdated()
    {
        if (configChangedCallback != null)
        {
            configChangedCallback.run();
        }
    }

    @Override
    public StationDao getStationDao()
    {
        if (stationDAO == null) {
            LOG.debug("creating new StationDAO ");
            synchronized (this) {
                if (stationDAO == null) {
                    stationDAO = new StationDaoJaxb(STATION_PATH);
                }
            }
        }
        return stationDAO;
    }

    @Override
    public TicketPricingDao getTicketPricingDao()
    {
        if (ticketPricingDao == null) {
            LOG.debug("creating new priceCalculatorDAO ");
            synchronized (this) {
                if (ticketPricingDao == null) {
                    ticketPricingDao = new TicketPricingDaoJaxb(PRICING_PATH);
                }
            }
        }
        return ticketPricingDao;
    }
}
