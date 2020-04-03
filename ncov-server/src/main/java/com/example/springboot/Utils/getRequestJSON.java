package com.example.springboot.Utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 发出html请求，返回json字段
 */
public class getRequestJSON {
    /**
     * 获取JSON对象
     * getJSONObjWithParameters(list, "https://lab.isaaclin.cn/nCoV/api/", "news");
     * @param parameters 参数
     * @param baseURL 基地址
     * @param function 接口名
     * @return JSON对象
     */
    public static JSONObject getJSONObjWithParameters(Map<String, String> parameters, String baseURL,
                                                      String function){
        // 确定访问的URL
        StringBuilder sb = new StringBuilder();
        String url = "";
        sb.append(baseURL).append(baseURL.charAt(baseURL.length() - 1) == '/' ? "" : "/").append(function).append("?");
        for(Map.Entry<String, String> kv: parameters.entrySet()){
            String key = kv.getKey();
            String value = kv.getValue();
            sb.append(key).append("=").append(value).append("&");
        }
        url = sb.toString();
        if(sb.charAt(sb.length()-1)=='&' || sb.charAt(sb.length()-1)=='?') {
            // 移除末尾的&符号
            url = url.substring(0, url.length()-1);
        }
        System.out.println("URL: "+url);

        return getJSONObjWithURL(url);
    }

    public static JSONObject getJSONObjWithURL(String URL) {
        // 获取信息
        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String raw_data = rest.getForObject(URL, String.class);

        // 转化为JSON对象
        JSONObject object = JSONObject.parseObject(raw_data);
        return object;
    }
}
