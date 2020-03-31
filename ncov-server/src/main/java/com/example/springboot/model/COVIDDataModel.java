package com.example.springboot.model;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.pojo.COVIDData;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * 运行时初始化，顺序1
 * 加载COVID数据
 */
@Component
@Order(1)
@EnableScheduling
public class COVIDDataModel implements ApplicationRunner {

    private static COVIDData data = null;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(this.getClass().getName()+": Init COVIDDataGetController");
        getCOVIDDataFromAPI();
        System.out.println(this.getClass().getName()+": "+"COVID Data Loaded.");
    }

    @Scheduled(initialDelay=2000,fixedRate=1000*60*60*6+10000)      // 每隔六小时更新一次
    public void getCOVIDDataFromAPI() throws InterruptedException {
        // 延迟2s，防止503错误
        // Thread.sleep(2000);
        getCOVIDData();
    }

    public void getCOVIDData() throws InterruptedException {

        System.out.println(LogGenerate.log(this.getClass(), "Updating COVID Data."));
        // 获取信息
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String raw_data = rest.getForObject("https://lab.isaaclin.cn/nCoV/api/overall?latest=1", String.class);

        // 转化为JSON对象
        JSONObject object = JSONObject.parseObject(raw_data);
        String resStr = object.getString("results");
        if(resStr.length()<=2) return;
        resStr = resStr.substring(1,resStr.length()-1);
        JSONObject resObj = JSONObject.parseObject(resStr);

        // JSON对象转类对象
        data = JSONObject.toJavaObject(resObj, COVIDData.class);
    }

    public static COVIDData getData() {
        return data;
    }

    public static int getCurrentConfirmedCount() {
        return data.getCurrentConfirmedCount();
    }

    public static int getCurrentConfirmedIncr() {
        return data.getCurrentConfirmedIncr();
    }

    public static int getConfirmedCount() {
        return data.getConfirmedCount();
    }

    public static int getConfirmedIncr() {
        return data.getConfirmedIncr();
    }

    public static int getSuspectedCount() {
        return data.getSuspectedCount();
    }

    public static int getSuspectedIncr() {
        return data.getSuspectedIncr();
    }

    public static int getCuredCount() {
        return data.getCuredCount();
    }

    public static int getCuredIncr() {
        return data.getCuredIncr();
    }

    public static int getDeadCount() {
        return data.getDeadCount();
    }

    public static int getDeadIncr() {
        return data.getDeadIncr();
    }

    public static int getSeriousCount() {
        return data.getSeriousCount();
    }

    public static int getSeriousIncr() {
        return data.getSeriousIncr();
    }

    public static String getGeneralRemark() {
        return data.getGeneralRemark();
    }

    public static String getRemark1() {
        return data.getRemark1();
    }

    public static String getRemark2() { return data.getRemark2(); }

    public static String getRemark3() {
        return data.getRemark3();
    }

    public static String getRemark4() {
        return data.getRemark4();
    }

    public static String getRemark5() {
        return data.getRemark5();
    }

    public static String getNote1() {
        return data.getNote1();
    }

    public static String getNote2() {
        return data.getNote2();
    }

    public static String getNote3() {
        return data.getNote3();
    }

    public static String getUpdateTime() {
        return data.getUpdateTime();
    }

}
