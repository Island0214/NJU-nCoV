package com.example.springboot.model;

import java.util.List;

public class CountryData {
    // 国家
    private String Country;
    // 病情情况数组
    private List<CountryDailyData> dailyData;

    public CountryData(String country) {
        Country = country;
    }

    // Getter & Setter
    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public List<CountryDailyData> getDailyData() {
        return dailyData;
    }

    public void setDailyData(List<CountryDailyData> dailyData) {
        this.dailyData = dailyData;
    }

    // 添加数据
    public void addDailyData(CountryDailyData cdd){
        this.dailyData.add(cdd);
    }
}
