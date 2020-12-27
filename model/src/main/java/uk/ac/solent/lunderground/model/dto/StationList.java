package uk.ac.solent.lunderground.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "StationList")
@XmlAccessorType(XmlAccessType.FIELD)
public class StationList
{
    @XmlElement(name = "Station")
    private List<Station> stations = null;

    public List<Station> getStations()
    {
        return this.stations;
    }

    public void setStations(List<Station> stations)
    {
        this.stations = stations;
    }
}
