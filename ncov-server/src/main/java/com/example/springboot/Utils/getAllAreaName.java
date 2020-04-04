package com.example.springboot.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.pojo.COVIDNews;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于获取当前病情数据的所有地区的中文名称和英文名称的工具类
 */
public class getAllAreaName {

    public static void getProvinceNameZH() {
        List<String> zhList = new ArrayList<>();

        String url = "https://lab.isaaclin.cn/nCoV/api/provinceName?lang=zh";
        JSONObject zh_allProvince = getRequestJSON.getJSONObjWithURL(url);
        String results = zh_allProvince.getString("results");
        JSONArray zh_provinceName = JSONArray.parseArray(results);

        String filePath = "src/main/resources/static/zh_allProvince.json";
        try {
            for(int i=0; i<zh_provinceName.size(); i++) {
                String name = zh_provinceName.get(i).toString();
                zhList.add(name);
            }
            JSONArray zhJSONArray = JSONArray.parseArray(JSON.toJSONString(zhList));

            Writer write = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
            write.write(zhJSONArray.toJSONString());
            write.flush();
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getProvinceNameZH();
    }

}
