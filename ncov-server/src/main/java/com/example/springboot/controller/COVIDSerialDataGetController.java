package com.example.springboot.controller;

import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.model.OverallInfoReaderModel;
import com.example.springboot.pojo.AreaDailyData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class COVIDSerialDataGetController {

    @RequestMapping(value = "/api/getCountrySerial", method = RequestMethod.GET)
    public List<AreaDailyData> getCountrySerial(
            @RequestParam(value = "country", required = true) String country) {
        System.out.println(LogGenerate.log(this.getClass(), "getCountrySerial"));
        return OverallInfoReaderModel.getCountryDailyData(country);
    }

    @RequestMapping(value = "/api/getProvinceSerial", method = RequestMethod.GET)
    public List<AreaDailyData> getProvinceSerial(
            @RequestParam(value = "province", required = true) String province) {
        System.out.println(LogGenerate.log(this.getClass(), "getProvinceSerial"));
        return OverallInfoReaderModel.getProvinceDailyData(province);
    }

    @RequestMapping(value = "/api/getCitySerial", method = RequestMethod.GET)
    public List<AreaDailyData> getCitySerial(
            @RequestParam(value = "city", required = true) String city) {
        System.out.println(LogGenerate.log(this.getClass(), "getCitySerial"));
        return OverallInfoReaderModel.getCityDailyData(city);
    }
}
