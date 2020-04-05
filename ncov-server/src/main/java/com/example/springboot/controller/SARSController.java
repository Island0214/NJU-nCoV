package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.Utils.LogGenerate;
import com.example.springboot.pojo.Country;
import com.example.springboot.pojo.SingleData;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.io.*;
import java.util.*;

/**
 * vue 用法 <script>中
 * export default {
 *   name: 'RequestSarsData',
 *   data () {
 *     return {
 *       DataForm: {
 *         country: '',
 *         year: '',
 *         month: '',
 *         day: ''
 *       },
 *       responseResult: []
 *     }
 *   },
 *   methods: {
 *     query () {
 *       this.$axios
 *         .post('/sars', {
 *           country: this.DataForm.country,
 *           year: this.DataForm.year,
 *           month: this.DataForm.month,
 *           day: this.DataForm.day
 *         })
 *         .then(successResponse => {
 *           document.getElementById('sars_res').innerText = successResponse.data
 *         })
 *         .catch(failResponse => {})
 *     }
 *   }
 * }
 */

@RestController
public class SARSController implements ApplicationRunner {

    static List<SingleData> lists = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // String path = SARSController.class.getClassLoader().getResource("static/sars.json").getPath();
        String classPath = "static/sars.json";
        // String s = readJsonFile(path);
        String s = readJsonFileInputStream(classPath);
        JSONArray jarr = JSON.parseArray(s);
        lists = JSONArray.parseArray(jarr.toJSONString(), SingleData.class);
    }

    @CrossOrigin
    @PostMapping(value = "api/sars")
    @ResponseBody
    public String requestSARSData(@RequestBody Country country){
        // 传入 国家名, year, month, day
        // 返回 "确诊-死亡-疑似"
        String c_name = country.getCountry();
        String date_year = country.getYear();
        String date_month = country.getMonth();
        String date_day = country.getDay();

        int[] res = getCaseNumber(c_name, Integer.parseInt(date_year), Integer.parseInt(date_month)
                , Integer.parseInt(date_day));

        if(res != null) {
            return res[0] + "-" + res[1] + "-" + res[2];
        }else{
            return null;
        }
    }

    @RequestMapping(value = "/api/getAllSarsData", method = RequestMethod.GET)
    public List<SingleData> getAllSarsData(
            @RequestParam(value = "countryName", required = false) String countryName) {
        System.out.println(LogGenerate.log(this.getClass(), "Request all SARS Data."));
        return lists;
    }

    public SARSController() {

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

        SingleData res = null;
        for(SingleData sd: lists){
            if(sd.getYear()==year && sd.getMonth()==month && sd.getDay()==day){
                res = sd;
                break;
            }
        }

        if (res!=null){
            int[] data = new int[]{res.getConfirm(), res.getDead(), res.getSuspect()};
            System.out.println("Return: "+Arrays.toString(data));
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

    public static void main(String[] args) {
        int[] res = getCaseNumber("China", 2003, 6, 25);
        if(res!=null) {
            System.out.println("Confirm: " + res[0] + ", Dead: " + res[1] + ", Suspect: " + res[2]);
        }
    }
}
