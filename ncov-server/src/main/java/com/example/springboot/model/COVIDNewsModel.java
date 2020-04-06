package com.example.springboot.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Constants;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.pojo.COVIDNews;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.naming.Context;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 运行时初始化，顺序2
 * 加载COVID新闻
 */
@Component
//@Order(2)
@EnableScheduling
public class COVIDNewsModel {

    private static List<COVIDNews> newsList = new ArrayList<>();

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(LogGenerate.log(this.getClass(), "Init COVIDNewsModel."));
//        // 初始化List
//        getCOVIDNewsFromAPI();
//        System.out.println(LogGenerate.log(this.getClass(), "COVID News Loaded."));
//    }

    @Scheduled(initialDelay=2000, fixedRate=1000*60*60*6+20000)      // 每隔六小时更新一次
    public void getCOVIDNewsFromAPI() throws InterruptedException {
        // 延迟2s，防止503错误
        // Thread.sleep(2000);

        getCOVIDNews(null, null, Constants.NEWSCOUNT, null);
        while (newsList.size()==0) {
            Thread.sleep((long) (Math.random()*2000));
            getCOVIDNews(null, null, Constants.NEWSCOUNT, null);
        }
    }

    public void getCOVIDNews(String province, Integer page, Integer num, String lang) {
        newsList = new ArrayList<>();

        // 确定访问的URL
        StringBuilder sb = new StringBuilder();
        String url = "";
        sb.append("https://lab.isaaclin.cn/nCoV/api/news?");
        if(province!=null) sb.append("province=").append(province).append("&");
        if(page!=null) sb.append("page=").append(page).append("&");
        if(num!=null) sb.append("num=").append(num).append("&");
        if(lang!=null) sb.append("lang=").append(lang).append("&");
        url = sb.toString();
        if(sb.charAt(sb.length()-1)=='&' || sb.charAt(sb.length()-1)=='?') {
            // 移除末尾的&符号
            url = url.substring(0, url.length()-1);
        }
        System.out.println("URL: "+url);

        // 获取信息
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String raw_data = rest.getForObject(url, String.class);

        // 转化为JSON对象
        JSONObject object = JSONObject.parseObject(raw_data);

        String arrStr = object.getString("results");
        JSONArray newsArr = JSONArray.parseArray(arrStr);
        for(int i=0; i<newsArr.size(); i++) {
            JSONObject jsonObject = newsArr.getJSONObject(i);
            newsList.add(JSONObject.toJavaObject(jsonObject, COVIDNews.class));
        }
    }

    public static List<COVIDNews> getNewsList() {
        return newsList;
    }


}
