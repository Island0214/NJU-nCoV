package com.example.springboot.crawler.utils;

import com.example.springboot.crawler.model.SARSSingle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataCrawler {

    public static void main(String[] args) throws IOException, URISyntaxException {
        //String url = "https://www.who.int/csr/sars/country/2003_19_03/en/";
        String url = "https://www.who.int/csr/sars/country/en/";

        GetRawHTML getRawHTML = new GetRawHTML();
        String raw = getRawHTML.getRawHTML(url);

        // 1. 将文档解析为Document
        Document doc = Jsoup.parse(raw);

        // 2. 根据标签提取数据
        System.out.println("<<---->>");

        // 3. 获取所有的SARS链接
        Elements elements = doc.select("div[class=col_2-1_1]").select("ul[class=auto_archive]").select("li");
        List<SARSSingle> pages = new ArrayList<>();
        for(Element element: elements){
            Element dateElement = element.selectFirst("a");
            Element infoElement = element.selectFirst("span");
            pages.add(new SARSSingle(dateElement.text(), dateElement.attr("href"), infoElement.text()));
        }

        for(SARSSingle s: pages){
            System.out.println(s.toString());
        }

        // 4. 每个链接获取其详细信息
        File file = new File("info.txt");
        FileOutputStream fos = new FileOutputStream(file);
        for(SARSSingle page: pages){
            fos.write(page.toString().getBytes());
            fos.write("-------------------------------\n".getBytes());
            raw = getRawHTML.getRawHTML(page.getURL());
            // 4.1 将文档解析为Document
            doc = Jsoup.parse(raw);
            // 4.2 获取数据
            elements = doc.select("tr");
            for(Element element: elements){
                Elements tds = element.select("td");
                for(Element td: tds){
                    String value = td.text();
                    value = value.replaceAll("[^a-zA-Z0-9\\u0020]","");
                    value = value.trim();
                    fos.write((value+" , ").getBytes());
                }
                fos.write(("\n").getBytes());
                //fos.write((element.text()+"\n").getBytes());
                //System.out.println(element.text());
            }
            fos.write("======================================================================\n".getBytes());
        }
        fos.close();
    }
}
