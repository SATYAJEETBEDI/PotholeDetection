package com.example.potholeDetection.geodata;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/location")
public class LocationController {

    // @Autowired
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/live")
    public Optional<String> alert(@RequestBody Location location) {
        
        return locationService.googleDistance(location);
    }
    


    @PostMapping("/")
    void create(@RequestBody Location location) {
        locationService.create(location);
    }

    @GetMapping("/centroid")
    public void centroidAdd() {
        locationService.centroidAdd();
    }
    
    
    
}
