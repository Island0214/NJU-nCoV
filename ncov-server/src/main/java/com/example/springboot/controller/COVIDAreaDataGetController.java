package com.example.springboot.controller;

import com.example.springboot.Constants;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.model.COVIDAreaModel;
import com.example.springboot.model.COVIDNewsModel;
import com.example.springboot.pojo.COVIDNews;
import com.example.springboot.pojo.CountryData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class COVIDAreaDataGetController {

    @RequestMapping(value = "/api/areaData", method = RequestMethod.GET)
    public CountryData getCOVIDNews(@RequestParam(value = "provinceName", required = true) String provinceName) {
        System.out.println(LogGenerate.log(this.getClass(), "Request: "+provinceName));
        return COVIDAreaModel.getObjectByProvinceName(provinceName);
    }
}
