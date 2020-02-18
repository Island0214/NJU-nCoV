package com.example.springboot.pojo;

public class Country {
    String year;
    String month;
    String day;
    String country;

    public Country(String year, String month, String day, String country) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
