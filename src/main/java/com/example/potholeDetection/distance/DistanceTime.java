package com.example.potholeDetection.distance;

import java.io.IOException;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class DistanceTime {



    private static final String API_KEY="AIzaSyA8XQE8uI2ZiJp2ztuxPFSRS7sa38cBtI0";
    OkHttpClient client = new OkHttpClient();


public String calculate(String source ,String destination) throws IOException {
String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+source+"&destinations="+destination+"&key="+ API_KEY;
            Request request = new Request.Builder()
                .url(url)
                .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
          }


}