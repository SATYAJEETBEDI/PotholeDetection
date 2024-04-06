package com.example.potholeDetection.geodata;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/location")
public class LocationController {

    // @Autowired
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostMapping("/")
    void create(@RequestBody Location location) {
        locationRepository.save(location);
    }
    
    @DeleteMapping("/")
    void delete(@RequestBody Location location) {
        locationRepository.delete(locationRepository.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude()));
    }
    
}
