package com.example.potholeDetection.distance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.potholeDetection.geodata.Location;

@RestController
@RequestMapping("/api/distance")
public class DistanceController {

   DistanceService distanceService;

    public DistanceController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping("/live")
    public void alert(@RequestBody Location location) {
         distanceService.calculate(location);;
    }
}
