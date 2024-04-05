package com.example.potholeDetection.geodata;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostMapping("/")
    void create(@RequestBody Location location) {
        locationRepository.save(location);
    }
    
    @DeleteMapping("/")
    void delete(@RequestParam double longitude, @RequestParam double latitude) {
        locationRepository.delete(locationRepository.findByLatitudeAndLongitude(latitude, longitude));
    }
    
}
