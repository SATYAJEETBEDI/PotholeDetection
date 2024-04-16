package com.example.potholeDetection.geodata;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.potholeDetection.clustering.Centroid;
import com.example.potholeDetection.clustering.CentroidRepository;
import com.example.potholeDetection.clustering.KMeansClustering;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final KMeansClustering kMeansClustering;
    private final CentroidRepository centroidRepository;
    
    private final int k = 2;


    public LocationService(LocationRepository locationRepository, KMeansClustering kMeansClustering, CentroidRepository centroidRepository) {
        this.locationRepository = locationRepository;
        this.kMeansClustering = kMeansClustering;
        this.centroidRepository = centroidRepository;
    }

    public Optional<String> googleDistance(Location location) {
        //implementation of google distance matrix api pending
        return Optional.of("SOMETHING");
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public void create(Location location) {
         //checking for duplicate according to distance is pending
        //if(locationRepository.findByLatitudeAndLongitude(location.getLatitude(), location.getLongitude())==null){
        locationRepository.save(location);
    }

    public void centroidAdd() {

        try {
            List<Centroid> centroids=kMeansClustering.findCentroids(getAllLocations(), k);
            centroidRepository.deleteAll();
            for(Centroid centroid:centroids){
                centroidRepository.save(centroid);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
