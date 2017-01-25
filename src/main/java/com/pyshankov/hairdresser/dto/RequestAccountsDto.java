package com.pyshankov.hairdresser.dto;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pyshankov on 1/23/17.
 */
public class RequestAccountsDto {

    private Double longitude;

    private Double latitude;

    private Double distance;

    private int offset;

    private int limit;


    public RequestAccountsDto(Double longitude, Double latitude, Double distance, int offset, int limit) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
        this.offset = offset;
        this.limit = limit;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
