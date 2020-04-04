package com.example.springboot.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Constants;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.pojo.COVIDRumor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 运行时初始化，顺序3
 * 加载COVID谣言
 */
@Component
//@Order(3)
@EnableScheduling
public class COVIDRumorModel {

    private static List<COVIDRumor> rumorList = new ArrayList<>();

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println(LogGenerate.log(this.getClass(), "Init COVIDRumorModel."));
//        // 初始化List
//        getCOVIDRumorsFromAPI();
//        System.out.println(LogGenerate.log(this.getClass(), "COVID Rumors Loaded."));
//    }

    @Scheduled(initialDelay=3000, fixedRate=1000*60*60*6+30000)      // 每隔六小时更新一次
    public void getCOVIDRumorsFromAPI() throws InterruptedException {
        // 延迟2s，防止503错误
        // Thread.sleep(2000);

        getCOVIDRumors(null, null, Constants.RUMORCOUNT);
    }

    public void getCOVIDRumors(String rumorType, Integer page, Integer num) {
        rumorList = new ArrayList<>();

        // 确定访问的URL
        StringBuilder sb = new StringBuilder();
        String url = "";
        sb.append("https://lab.isaaclin.cn/nCoV/api/rumors?");
        if(rumorType!=null) sb.append("rumorType=").append(rumorType).append("&");
        if(page!=null) sb.append("page=").append(page).append("&");
        if(num!=null) sb.append("num=").append(num).append("&");
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
            rumorList.add(JSONObject.toJavaObject(jsonObject, COVIDRumor.class));
        }
    }

    public static List<COVIDRumor> getRumorList() {
        return rumorList;
    }
}
