package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.springboot.pojo.SingleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
public class SARSGetController {

    @Autowired
    private static List<SingleData> lists;

    /**
     * 用法示例："http://127.0.0.1:8444/api/sarsGet?country=china&&year=2003&&month=5&&day=3"
     * @param country
     * @param year
     * @param month
     * @param day
     * @return "确诊-死亡-疑似"
     */
    @RequestMapping(value = "/api/sarsGet", method = RequestMethod.GET)
    public String getSARSData(@RequestParam("country") String country,
                        @RequestParam("year") String year,
                        @RequestParam("month") String month,
                        @RequestParam("day") String day) {
        int[] res = getCaseNumber(country, Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

        if(res != null) {
            return res[0] + "-" + res[1] + "-" + res[2];
        }else{
            return "NotFound";
        }
    }

    /**
     * 根据国家[暂时不用], 年月日来返回当日SARS信息
     * @param country
     * @param year
     * @param month
     * @param day
     * @return int数组, [0]->确诊, [1]->死亡, [2]->疑似
     */
    private static int[] getCaseNumber(String country, int year, int month, int day){
        System.out.println("Request: "+country+", "+year+", "+month+", "+day);
        String path = SARSController.class.getClassLoader().getResource("static/sars.json").getPath();
        String s = readJsonFile(path);
        JSONArray jarr = JSON.parseArray(s);
        List<SingleData> lists = JSONArray.parseArray(jarr.toJSONString(), SingleData.class);

        SingleData res = null;
        for(SingleData sd: lists){
            if(sd.getYear()==year && sd.getMonth()==month && sd.getDay()==day){
                res = sd;
                break;
            }
        }

        if (res!=null){
            int[] data = new int[]{res.getConfirm(), res.getDead(), res.getSuspect()};
            System.out.println("Return: "+ Arrays.toString(data));
            return data;
        }else{
            System.out.println("Return: null");
            return null;
        }
    }

    private static String readJsonFile(String fileName){
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
