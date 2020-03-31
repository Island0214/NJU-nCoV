package com.example.springboot.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Constants;
import com.example.springboot.Utils.TimeStampUtil;
import com.example.springboot.model.COVIDNewsModel;
import com.example.springboot.pojo.COVIDNews;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class COVIDNewsGetController {

    public List<COVIDNews> newsList = new ArrayList<>();
    /**
     * 返回相关的新闻信息
     *
     * @param province
     * @param page
     * @param num
     * @param lang
     * @return
     */
    @RequestMapping(value = "/api/COVIDNews", method = RequestMethod.GET)
    public String getNews(@RequestParam(value="province",required = false) String province,
                          @RequestParam(value="page",required = false) Integer page,
                          @RequestParam(value="num",required = false) Integer num,
                          @RequestParam(value="lang",required = false) String lang) {

        // System.out.printf("---Params---\nProvince: %s\nPage: %d\nNum: %d\nLang: %s\n",province,page,num,lang);
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

        sb = new StringBuilder();
        for(COVIDNews news: newsList) {
            sb.append(news.toString()).append("\n");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/api/news", method = RequestMethod.GET)
    public List<COVIDNews> getCOVIDNews(@RequestParam(value = "num", required = false) Integer num) {
        if(num != null) {
            if(num <= Constants.NEWSCOUNT)
                return COVIDNewsModel.getNewsList().subList(0,num);
            else
                return COVIDNewsModel.getNewsList();
        } else {
            return COVIDNewsModel.getNewsList();
        }
    }


    public static void main(String[] args) {
        System.out.println(TimeStampUtil.stampToDate("1585053527000"));
    }
}
