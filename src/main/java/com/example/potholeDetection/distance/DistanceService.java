package com.example.potholeDetection.distance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.potholeDetection.geodata.Location;
import com.example.potholeDetection.geodata.LocationRepository;

@Service
public class DistanceService {

    DistanceCalculatorService distanceCalculatorService;
    LocationRepository locationRepository;


    public DistanceService(DistanceCalculatorService distanceCalculatorService,LocationRepository locationRepository) {
        this.distanceCalculatorService = distanceCalculatorService;
        this.locationRepository = locationRepository;
    }

    

    public void calculate(Location destination) {
        List<Location> allLocation=locationRepository.findAll();
        List<Double> toPrint=new ArrayList<>();
        for(Location location:allLocation){
            try {
                distanceCalculatorService.getData(location, destination);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // toPrint.add(distance);
            // System.out.println("Distance is "+distance);
        }
        // for(Double d:toPrint){
        //     System.out.println(d);
        // }
    }
}
