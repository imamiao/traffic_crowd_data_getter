package com.amap.traffic_crowd_data;

public class ReceivedData {

    private float[] serieData;
    private String[] categories;

    public float[] getSerieData() {
        return serieData;
    }

    public void setSerieData(float[] serieData) {
        this.serieData = serieData;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public ReceivedData(float[] serieData, String[] categories) {
        this.serieData = serieData;
        this.categories = categories;
    }

    public ReceivedData() {
    }
}
