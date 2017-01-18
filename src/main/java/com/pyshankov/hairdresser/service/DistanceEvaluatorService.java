package com.pyshankov.hairdresser.service;

import com.pyshankov.hairdresser.domain.Location;

/**
 * Created by p.marchenko on 1/18/2017.
 */
public class DistanceEvaluatorService {

    public final static double EARTH_RADIUS = 6371;

    public static double length(Location location1, Location location2){
        double dLat = deg2rad(location2.getLatitude()-location1.getLatitude());
        double dLon = deg2rad(location2.getLongitude()-location1.getLongitude());
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(location1.getLatitude())) * Math.cos(deg2rad(location2.getLatitude())) *
                                Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = EARTH_RADIUS * c; // Distance in km
        return d;
    }

    private static double deg2rad(double deg){
        return deg * (Math.PI/180);
    }
}
