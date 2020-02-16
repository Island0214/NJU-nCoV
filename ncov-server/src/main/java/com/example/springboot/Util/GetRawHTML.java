package com.example.springboot.Util;

import com.google.common.collect.Lists;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GetRawHTML {
    public String getRawHTML(String url) throws URISyntaxException, IOException {
        //String url = "https://www.who.int/csr/sars/country/2003_19_03/en/";    //请求路径

        //构造路径参数
        List<NameValuePair> nameValuePairList = Lists.newArrayList();
        //nameValuePairList.add(new BasicNameValuePair("username","test"));
        //nameValuePairList.add(new BasicNameValuePair("password","password"));

        //构造请求路径，并添加参数
        URI uri = new URIBuilder(url).addParameters(nameValuePairList).build();

        //构造Headers
        List<Header> headerList = Lists.newArrayList();
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9," +
                "image/webp,image/apng,*/*;q=0.8"));
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HttpHeaders.CACHE_CONTROL, "max-age=0"));
        headerList.add(new BasicHeader(HttpHeaders.CONNECTION, "keep-alive"));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4,ja;q=0.2," +
                "de;q=0.2"));

        //构造HttpClient
        HttpClient httpClient = HttpClients.custom().setDefaultHeaders(headerList).build();

        //构造HttpGet请求
        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(uri).build();

        //获取结果
        HttpResponse httpResponse = httpClient.execute(httpUriRequest);

        //获取返回结果中的实体
        HttpEntity entity = httpResponse.getEntity();

        //查看页面内容结果
        String rawHTMLContent = EntityUtils.toString(entity);
        //rawHTMLContent = rawHTMLContent.substring(rawHTMLContent.indexOf("<tr>"),rawHTMLContent.length());

        //System.out.println(rawHTMLContent);

        //关闭HttpEntity流
        EntityUtils.consume(entity);

        return rawHTMLContent;
    }
}
