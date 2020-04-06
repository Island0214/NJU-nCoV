package com.example.springboot.pojo;

public class AreaDailyData {
    String date;
    String country;
    String countryCode;
    String province;
    String provinceCode;
    String city;
    String cityCode;

    Integer confirmed;
    Integer suspected;
    Integer cured;
    Integer dead;

    @Override
    public String toString() {
        return "AreaDailyData{" +
                "date='" + date + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", province='" + province + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", city='" + city + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", confirmed=" + confirmed +
                ", suspected=" + suspected +
                ", cured=" + cured +
                ", dead=" + dead +
                '}';
    }

    /**
     * 返回数据类型
     * 1. 国家数据
     * 2. 省份数据
     * 3. 城市数据
     * 0. 其他类型数据
     * @return
     */
    public int getCategory() {
        if((province==null || province.equals("")) && (city==null || city.equals(""))) {
            return 1;
        } else if (city==null || city.equals("")) {
            return 2;
        } else {
            return 3;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getSuspected() {
        return suspected;
    }

    public void setSuspected(Integer suspected) {
        this.suspected = suspected;
    }

    public Integer getCured() {
        return cured;
    }

    public void setCured(Integer cured) {
        this.cured = cured;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }
}
