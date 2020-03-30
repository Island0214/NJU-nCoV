package com.example.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.pojo.COVIDNews;
import com.example.springboot.pojo.COVIDRumor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class COVIDRumorGetController {

    public List<COVIDRumor> rumorList = new ArrayList<>();

    /**
     * 返回流言信息
     * @param rumorType
     * @param page
     * @param num
     * @return
     */
    @RequestMapping(value = "/api/COVIDRumor", method = RequestMethod.GET)
    public String getRumors(@RequestParam(value = "rumorType", required = false) String rumorType,
                            @RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "num", required = false) Integer num) {
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

        sb = new StringBuilder();
        for(COVIDRumor news: rumorList) {
            sb.append(news.toString()).append("\n");
        }
        return sb.toString();
    }


}
