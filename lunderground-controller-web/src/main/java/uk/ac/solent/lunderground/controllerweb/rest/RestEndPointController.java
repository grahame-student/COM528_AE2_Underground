package uk.ac.solent.lunderground.controllerweb.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.solent.lunderground.model.dto.Station;
import uk.ac.solent.lunderground.model.dto.StationList;

import java.util.ArrayList;
import java.util.List;

@RestController("/rest/v1")
public class RestEndPointController
{
    @RequestMapping(value = "/rest/v1", method = RequestMethod.GET)
    public String testMapping()
    {
        return "HelloWorld";
    }

//    Station station = new Station();
//        station.setName("abc");
//        station.setZone(123);
//
//    List<Station> stations = new ArrayList<>();
//        stations.add(station);
//
//    StationList stationList= new StationList();
//        stationList.setStationList(stations);
//
//        return stationList;
}
