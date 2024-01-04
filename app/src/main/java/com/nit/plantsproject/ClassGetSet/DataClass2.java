package com.nit.plantsproject.ClassGetSet;

public class DataClass2 {

    private String dataSchedule;
    private String dataDay;
    private String dataGuest;
    private String dataTime;
    private String dataImage;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataSchedule() {
        return dataSchedule;
    }

    public String getDataDay() {
        return dataDay;
    }

    public String getDataGuest() {
        return dataGuest;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getDataImage() { return dataImage; }

    public DataClass2(String dataSchedule, String dataDay, String dataGuest, String dataTime, String dataImage) {
        this.dataSchedule = dataSchedule;
        this.dataDay = dataDay;
        this.dataGuest = dataGuest;
        this.dataTime = dataTime;
        this.dataImage = dataImage;
    }

    public DataClass2(){

    }
}
