package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.pojo.COVIDData;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class COVIDDataGetController {


    @RequestMapping(value = "/api/COVIDGet", method = RequestMethod.GET)
    public String getData() throws IOException {
        // 获取信息
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String raw_data = rest.getForObject("https://lab.isaaclin.cn/nCoV/api/overall?latest=1", String.class);

        // 转化为JSON对象
        JSONObject object = JSONObject.parseObject(raw_data);
        String resStr = object.getString("results");
        if(resStr.length()<=2) return "null";
        resStr = resStr.substring(1,resStr.length()-1);
        JSONObject resObj = JSONObject.parseObject(resStr);

        // JSON对象转类对象
        COVIDData data = JSONObject.toJavaObject(resObj, COVIDData.class);

        return data.toString();
    }

}
