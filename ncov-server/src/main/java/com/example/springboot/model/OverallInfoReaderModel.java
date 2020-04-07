package com.example.springboot.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.pojo.AreaDailyData;
import com.example.springboot.pojo.SingleData;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;

@Component
@RestController
public class OverallInfoReaderModel implements ApplicationRunner {

    public static Map<String, List<AreaDailyData>> countryDailyData = new HashMap<>();
    public static Map<String, List<AreaDailyData>> provinceDailyData = new HashMap<>();
    public static Map<String, List<AreaDailyData>> cityDailyData = new HashMap<>();

    public static List<AreaDailyData> getCountryDailyData(String country) {
        return countryDailyData.get(country);
    }

    public static List<AreaDailyData> getProvinceDailyData(String province) {
        return provinceDailyData.get(province);
    }

    public static List<AreaDailyData> getCityDailyData(String city) {
        return countryDailyData.get(city);
    }

    public static AreaDailyData getCountryLatest(String country) {
        List<AreaDailyData> list = countryDailyData.get(country);
        if(list==null || list.size()==0) return null;
        return list.get(list.size()-1);
    }

    public static AreaDailyData getProvinceLatest(String province) {
        List<AreaDailyData> list = provinceDailyData.get(province);
        return list.get(list.size()-1);
    }

    public static AreaDailyData getCityLatest(String city) {
        List<AreaDailyData> list = cityDailyData.get(city);
        return list.get(list.size()-1);
    }

    public static List<AreaDailyData> getAllCountryLatest() {
        List<AreaDailyData> res = new ArrayList<>();
        for(Map.Entry<String, List<AreaDailyData>> entry: countryDailyData.entrySet()) {
            List<AreaDailyData> list = entry.getValue();
            res.add(list.get(list.size()-1));
        }
        return res;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        String classPath = "static/Wuhan-2019-nCoV.json";
        String s = readJsonFileInputStream(classPath);
        JSONArray jarr = JSON.parseArray(s);
        System.out.println(jarr.size());
        List<AreaDailyData> lists = new ArrayList<>();
        lists = JSONArray.parseArray(jarr.toJSONString(), AreaDailyData.class);

        for(AreaDailyData single: lists) {
            int type = single.getCategory();
            String date = single.getDate();
            if (type == 1) {
                // 国家单日数据
                String country = single.getCountry();
                List<AreaDailyData> list = countryDailyData.get(country);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(single);
                countryDailyData.put(country, list);
            } else if (type == 2) {
                // 省份单日数据
                String province = single.getProvince();
                List<AreaDailyData> list = provinceDailyData.get(province);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(single);
                provinceDailyData.put(province, list);
            } else if (type == 3) {
                // 城市单日数据
                String city = single.getCity();
                List<AreaDailyData> list = cityDailyData.get(city);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(single);
                cityDailyData.put(city, list);
            }
        }
        System.out.println(LogGenerate.log(this.getClass(), "加载了每日数据, "+countryDailyData.size()+","+provinceDailyData.size()+","+cityDailyData.size()));
    }

    private static String readJsonFileInputStream(String classPath) {
        StringBuilder jsonStr = new StringBuilder();
        try {
            // 采用InputStream的方式进行读取，防止打包成jar之后无法获取到资源路径地址
            InputStream is = new ClassPathResource(classPath).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = reader.readLine())!=null) {
                jsonStr.append(line);
            }
            return jsonStr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int getCategory(AreaDailyData data) {
        return data.getCategory();
    }

    public static void main(String[] args) {
//        String classPath = "static/Wuhan-2019-nCoV.json";
//        // String s = readJsonFile(path);
//        String s = readJsonFileInputStream(classPath);
//        JSONArray jarr = JSON.parseArray(s);
//        System.out.println(jarr.size());
//        List<AreaDailyData> lists = new ArrayList<>();
//        lists = JSONArray.parseArray(jarr.toJSONString(), AreaDailyData.class);
        for(Map.Entry<String, List<AreaDailyData>> entry: countryDailyData.entrySet()) {
            System.out.print("\""+entry.getKey()+"\", ");
        }
    }
}
