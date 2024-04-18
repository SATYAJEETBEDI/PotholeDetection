package com.example.potholeDetection.distance;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.potholeDetection.geodata.Location;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorService {

    //downloading the data
    public  void getData(Location source,Location destination) throws Exception {
        String API_KEY = "AIzaSyA8XQE8uI2ZiJp2ztuxPFSRS7sa38cBtI0"; // Replace with your actual API key
        double sourceLat = source.getLatitude();
        // System.out.println(sourceLat);
        double sourceLng = source.getLongitude();
        // System.out.println(sourceLng);
        double destLat = destination.getLatitude();
        // System.out.println(destLat);
        double destLng = destination.getLongitude();
        // System.out.println(destLng);

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + sourceLat + "," +  sourceLng+ "&destinations=" + destLat + "," + destLng + "&key=" + API_KEY;
        
        var request = HttpRequest.newBuilder()
                                 .GET()
                                 .uri(URI.create(url))
                                 .build();
        
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body(); 

        long distance = -1L;
        long time = -1L;

        //parsing json data and updating data
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(response);
        JSONArray ja = (JSONArray) jo.get("rows");
        jo = (JSONObject) ja.get(0);
        ja = (JSONArray) jo.get("elements");
        jo = (JSONObject) ja.get(0);
        JSONObject je = (JSONObject) jo.get("distance");
        JSONObject jf = (JSONObject) jo.get("duration");
        distance = (long) je.get("value");
        time = (long) jf.get("value");

        System.out.println("Distance is " + distance + " meters");
        System.out.println("Time is " + time + " seconds");

        }       
        // System.out.println(response);
    }
    
        //return response;


    //public double getDistance(Location origin, Location destination) {
        // DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(geoApiContext)
        //         .origins(new LatLng(origin.getLatitude(), origin.getLongitude()))
        //         .destinations(new LatLng(destination.getLatitude(), destination.getLongitude()))
        //         .mode(TravelMode.DRIVING)
        //         .units(Unit.METRIC);
    
        // try {
        //     DistanceMatrix matrix = request.await();
        //     if (matrix.rows.length == 0 || matrix.rows[0].elements.length == 0) {
        //         // No distance information available
        //         return -1;
        //     }
        //     return matrix.rows[0].elements[0].distance.inMeters;
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     return -1;
        
         //}
    
