package com.pyshankov.hairdresser.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by pyshankov on 18.10.16.
 */
@Embeddable
public class Location {

    Location(){}

    @Column
    private String longitude;

    @Column
    private String latitude;

    public Location(String longtitude, String latitude) {
        this.longitude = longtitude;
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longitude;
    }

    public void setLongtitude(String longtitude) {
        this.longitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
