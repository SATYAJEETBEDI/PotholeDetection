package com.example.potholeDetection.distance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.potholeDetection.geodata.Location;

@RequestMapping("/api/distance")
public class DistanceController {

   DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping("/live")
    public String alert(@RequestBody Location location) {
        
        return distanceService.calculate(null, null);;
    }
}
