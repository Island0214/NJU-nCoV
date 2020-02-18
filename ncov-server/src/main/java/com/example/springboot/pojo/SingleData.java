package com.example.springboot.pojo;

public class SingleData {
    // 时期
    String date;
    // 确诊人数
    int confirm;
    // 死亡人数
    int dead;
    // 疑似人数
    int suspect;

    // 转化数值
    int year;
    int month;
    int day;

    public SingleData(String date, int confirm, int dead, int suspect) {
        this.date = date;
        this.confirm = confirm;
        this.dead = dead;
        this.suspect = suspect;
        String[] dateArr = date.split("-");
        this.year = Integer.parseInt(dateArr[0]);
        this.month = Integer.parseInt(dateArr[1]);
        this.day = Integer.parseInt(dateArr[2]);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getSuspect() {
        return suspect;
    }

    public void setSuspect(int suspect) {
        this.suspect = suspect;
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
}
