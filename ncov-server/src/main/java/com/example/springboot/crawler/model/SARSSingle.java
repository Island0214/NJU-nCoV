package com.example.springboot.crawler.model;

import java.util.Arrays;
import java.util.List;

public class SARSSingle {
    // 日期原始信息
    private String infoDate;
    // URL [未加前缀]
    private String suffixURL;
    // 原始info
    private String info;

    // 处理后信息
    private int year;
    private int month;
    private int day;
    private String URL;

    private List<String> cvArr = Arrays.asList("", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    public SARSSingle(String infoDate, String suffixURL, String info) {
        this.infoDate = infoDate;
        this.suffixURL = suffixURL;
        this.info = info;
        // 初始化
        String[] dateArr = infoDate.trim().split(" ");
        this.year = Integer.parseInt(dateArr[2], 10);
        this.month = cvArr.indexOf(dateArr[1]);
        this.day = Integer.parseInt(dateArr[0]);
        this.URL = "https://www.who.int" + suffixURL;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public String getSuffixURL() {
        return suffixURL;
    }

    public String getInfo() {
        return info;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getURL() {
        return URL;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Page Info:\n");
        sb.append("\t>"+"Date: "+this.year+"/"+this.month+"/"+this.day+"\n");
        sb.append("\t>"+"URL: "+this.URL+"\n");
        sb.append("\t>"+"Info: "+this.info+"\n");
        return sb.toString();
    }
}
