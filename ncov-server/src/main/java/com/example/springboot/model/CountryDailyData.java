package com.example.springboot.model;

public class CountryDailyData {
    // 国家
    private String Country;
    // 时间原始数据 [eg: 17 March 2003]
    private String timeInfo;
    // 年月日
    private int year;
    private int month;
    private int day;
    // 累积病情案例总数 [Cumulative Total number of case(s)]
    private int totalNumber;
    // 死亡总数 [Number of deaths]
    private int deathNumber;
    // 本地传播？ [Local transmission]
    private String transmission;

    // ---附加---
    // 病情增量 [阶段患病数]
    private int deltaNumber;
    // 死亡增量 [阶段死亡数]
    private int deltaDeath;

    // 构造方法
    public CountryDailyData(String country, String timeInfo, int totalNumber, int deathNumber, String transmission) {
        Country = country;
        this.timeInfo = timeInfo;
        this.totalNumber = totalNumber;
        this.deathNumber = deathNumber;
        this.transmission = transmission;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(int deathNumber) {
        this.deathNumber = deathNumber;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getDeltaNumber() {
        return deltaNumber;
    }

    public void setDeltaNumber(int deltaNumber) {
        this.deltaNumber = deltaNumber;
    }

    public int getDeltaDeath() {
        return deltaDeath;
    }

    public void setDeltaDeath(int deltaDeath) {
        this.deltaDeath = deltaDeath;
    }
}
