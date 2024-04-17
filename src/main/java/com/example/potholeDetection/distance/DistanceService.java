package com.example.potholeDetection.distance;

import java.io.IOException;

public class DistanceService {

    DistanceTime distanceTime;

    public DistanceService(DistanceTime distanceTime) {
        this.distanceTime = distanceTime;
    }

    public void calculate(String source, String destination) {
        try {
            System.out.println(distanceTime.calculate(source, destination));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
