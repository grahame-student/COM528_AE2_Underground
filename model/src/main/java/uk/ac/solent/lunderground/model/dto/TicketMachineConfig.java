package uk.ac.solent.lunderground.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketMachineConfig
{
    private String uuid = "";
    private String stationName = "";
    private int stationZone = 0;

    @XmlElementWrapper(name = "StationList")
    @XmlElement(name = "Station")
    private List<Station> stationList;

    private PricingSchedule pricingSchedule;

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public void setStation(Station station)
    {
        if (station != null)
        {
            stationName = station.getName();
            stationZone = station.getZone();
        }
    }

    public int getStationZone()
    {
        return stationZone;
    }

    public void setStationZone(int stationZone)
    {
        this.stationZone = stationZone;
    }

    public List<Station> getStationList()
    {
        return stationList;
    }

    public void setStationList(List<Station> stationList)
    {
        this.stationList = stationList;
    }

    public PricingSchedule getPricingSchedule()
    {
        return pricingSchedule;
    }

    public void setPricingSchedule(PricingSchedule pricingSchedule)
    {
        this.pricingSchedule = pricingSchedule;
    }

    @Override
    public String toString()
    {
        return "TicketMachineConfig{" +
               "uuid='" + uuid + '\'' +
               ", stationName='" + stationName + '\'' +
               ", stationZone=" + stationZone +
               ", stationList=" + stationList +
               ", pricingSchedule=" + pricingSchedule +
               '}';
    }
}
