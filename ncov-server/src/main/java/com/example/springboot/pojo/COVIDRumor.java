package com.example.springboot.pojo;

public class COVIDRumor {
    String title;
    String mainSummary;
    String body;
    String sourceUrl;

    @Override
    public String toString() {
        return "COVIDRumor{" +
                "title='" + title + '\'' +
                ", mainSummary='" + mainSummary + '\'' +
                ", body='" + body + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainSummary() {
        return mainSummary;
    }

    public void setMainSummary(String mainSummary) {
        this.mainSummary = mainSummary;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
