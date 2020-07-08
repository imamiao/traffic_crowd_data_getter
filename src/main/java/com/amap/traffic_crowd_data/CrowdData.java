package com.amap.traffic_crowd_data;

import java.util.Date;

public class CrowdData {
    private Integer id;
    private String cityCode;
    private Float crowd;
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Float getCrowd() {
        return crowd;
    }

    public void setCrowd(Float crowd) {
        this.crowd = crowd;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public CrowdData(Integer id, String cityCode, String cityName, Float crowd, Date time) {
        this.id = id;
        this.cityCode = cityCode;
        this.crowd = crowd;
        this.time = time;
    }
}
