package com.example.potholeDetection.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.example.potholeDetection.geodata.Location;

@Component
public class KMeansClustering {


    // public KMeansClustering(LocationService locationService) {
    //     this.locationService = locationService;
    // }

    // List<Location> locations = locationService.getAllLocations();
    // private static final int k=10;

    public List<Centroid> implement(List<Location> locations, int k) {
        // Initialize centroids randomly
        List<Centroid> centroids = initializeCentroids(locations, k);

        List<Centroid> previousCentroids;
        int count=0;
        do {
            // Assign each location to the nearest centroid
            List<List<Location>> clusters = assignLocationsToCentroids(locations, centroids);

            // Calculate new centroids based on the mean of each cluster
            previousCentroids = centroids;
            count++;
            centroids = calculateNewCentroids(clusters);
            System.out.println(count);

        } while (!hasConverged(previousCentroids, centroids));

        return centroids;
    }

    private static List<Centroid> initializeCentroids(List<Location> locations, int k) {
        List<Centroid> centroids = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            int index = random.nextInt(locations.size());
            Location location = locations.get(index);
            centroids.add(new Centroid(location.getLatitude(), location.getLongitude()));
        }
        return centroids;
    }

    private static List<List<Location>> assignLocationsToCentroids(List<Location> locations, List<Centroid> centroids) {
        List<List<Location>> clusters = new ArrayList<>();
        for (int i = 0; i < centroids.size(); i++) {
            clusters.add(new ArrayList<>());
        }

        for (Location location : locations) {
            double minDistance = Double.MAX_VALUE;
            int closestCentroidIndex = 0;

            for (int i = 0; i < centroids.size(); i++) {
                Centroid centroid = centroids.get(i);
                double distance = calculateDistance(location, centroid);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestCentroidIndex = i;
                }
            }

            clusters.get(closestCentroidIndex).add(location);
        }

        return clusters;
    }

    private static double calculateDistance(Location location, Centroid centroid) {
        return Math.sqrt(Math.pow(location.getLatitude() - centroid.getCentroidLatitude(), 2)
                + Math.pow(location.getLongitude() - centroid.getCentroidLongitude(), 2));
    }

    private static List<Centroid> calculateNewCentroids(List<List<Location>> clusters) {
        List<Centroid> newCentroids = new ArrayList<>();
        for (List<Location> cluster : clusters) {
            double sumLatitude = 0;
            double sumLongitude = 0;

            for (Location location : cluster) {
                sumLatitude += location.getLatitude();
                sumLongitude += location.getLongitude();
            }

            double newLatitude = sumLatitude / cluster.size();
            double newLongitude = sumLongitude / cluster.size();
            newCentroids.add(new Centroid(newLatitude, newLongitude));
        }
        return newCentroids;
    }

    private static boolean hasConverged(List<Centroid> previousCentroids, List<Centroid> currentCentroids) {
        if (previousCentroids.size() != currentCentroids.size()) {
            return false;
        }

        for (int i = 0; i < previousCentroids.size(); i++) {
            Centroid previousCentroid = previousCentroids.get(i);
            Centroid currentCentroid = currentCentroids.get(i);
            if (previousCentroid.getCentroidLatitude() != currentCentroid.getCentroidLatitude() || previousCentroid.getCentroidLongitude() != currentCentroid.getCentroidLongitude()) {
                return false;
            }
        }

        return true;
    }
}



