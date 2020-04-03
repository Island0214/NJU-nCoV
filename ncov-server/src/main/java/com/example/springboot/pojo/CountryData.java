package com.example.springboot.pojo;

/**
 * 变量名	                注释
 * locationId	            城市编号 中国大陆城市编号为邮编，中国大陆以外城市编号暂不知规则
 * continent(English)Name	大洲（英文）名称
 * country(English)Name	    国家（英文）名称
 * province(English)Name	省份、地区或直辖市（英文）全称
 * provinceShortName	    省份、地区或直辖市简称
 * currentConfirmedCount	现存确诊人数，值为confirmedCount - curedCount - deadCount
 * confirmedCount	        累计确诊人数
 * suspectedCount	        疑似感染人数
 * curedCount	            治愈人数
 * deadCount	            死亡人数
 * comment	                其他信息
 * cities	                下属城市的情况
 * updateTime	            数据更新时间
 */

public class CountryData {
    int locationId;
    String continentName;
    String continentEnglishName;
    String countryName;
    String countryEnglishName;
    String provinceName;
    String provinceEnglishName;
    String provinceShortName;
    int currentConfirmedCount;
    int confirmedCount;
    int suspectedCount;
    int curedCount;
    int deadCount;
    String comment;
    String cities;
    String updateTime;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getContinentEnglishName() {
        return continentEnglishName;
    }

    public void setContinentEnglishName(String continentEnglishName) {
        this.continentEnglishName = continentEnglishName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryEnglishName() {
        return countryEnglishName;
    }

    public void setCountryEnglishName(String countryEnglishName) {
        this.countryEnglishName = countryEnglishName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceEnglishName() {
        return provinceEnglishName;
    }

    public void setProvinceEnglishName(String provinceEnglishName) {
        this.provinceEnglishName = provinceEnglishName;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
    }

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
