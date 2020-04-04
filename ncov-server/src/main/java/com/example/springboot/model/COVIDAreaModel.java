package com.example.springboot.model;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.Utils.getRequestJSON;
import com.example.springboot.crawler.model.CountryDailyData;
import com.example.springboot.pojo.COVIDRumor;
import com.example.springboot.pojo.CountryData;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 运行时初始化，顺序1
 * 加载COVID 各区域的数据
 */
@Component
//@Order(4)
@EnableScheduling
public class COVIDAreaModel {
    private static List<CountryData> areaDataList = new ArrayList<>();

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(this.getClass().getName()+": Init COVIDAreaModel");
//        getCOVIDAreaDataFromAPI();
//        System.out.println(this.getClass().getName()+": "+"COVIDAreaModel Data Loaded.");
//    }

    @Scheduled(initialDelay=4000,fixedRate=1000*60*60*6+40000)      // 每隔六小时更新一次
    public void getCOVIDAreaDataFromAPI() throws InterruptedException {
        // 延迟2s，防止503错误
        // Thread.sleep(2000);

        getCOVIDAreaData(1);
    }

    public void getCOVIDAreaData(int latest){
        System.out.println(LogGenerate.log(this.getClass(), "get COVID Area Data."));
        // 转化为JSON对象
        JSONObject object = getRequestJSON.getJSONObjWithURL("https://lab.isaaclin.cn/nCoV/api/area?latest="+(latest==1?'1':'0'));
        String arrStr = object.getString("results");
        JSONArray areaDataArr = JSONArray.parseArray(arrStr);
        for(int i=0; i<areaDataArr.size(); i++) {
            JSONObject jsonObject = areaDataArr.getJSONObject(i);
            areaDataList.add(JSONObject.toJavaObject(jsonObject, CountryData.class));
        }
        System.out.println(LogGenerate.log(this.getClass(), "length:"+areaDataArr.size()));
    }

    /**
     * 按照省份的名称获取字段
     * @param areaName 中文省名称
     */
    public static CountryData getObjectByProvinceName(String areaName) {
        for(CountryData areaData: areaDataList) {
            if(areaData.getProvinceName().equals(areaName) || areaData.getProvinceShortName().equals(areaName)) {
                return areaData;
            }
        }
        return null;
    }

}
