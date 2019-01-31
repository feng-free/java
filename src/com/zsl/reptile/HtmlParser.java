package com.zsl.reptile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/*
 * @ClassName Htmlparser
 * @Description 网页解析 Jsoup解析html标签时类似于JQuery的一些符号
 * @Author 张双亮
 * @Version 1.0
 **/
public class HtmlParser {
	protected List<List<String>> data = new LinkedList<List<String>>();
	/**
	 * 获取value值
	 * @param e
	 * @return
	 */
	public static String getValue(Element e) {
		return e.attr("value");
	}
	
	/**
	 * 获取<tr>和</tr>之间的文本
	 * @param e
	 * @return
	 */
	public static String getText(Element e) {
		return e.text();
	}
	
	/**
	 * 识别属性id的标签,一般一个html页面id唯一
	 * @param body
	 * @param id
	 * @return
	 */
	public static Element getID(String body, String id) {
		Document doc = Jsoup.parse(body);
		// 所有#id的标签
		Elements elements = doc.select("#" + id);
		// 返回第一个
		return elements.first();
	}
	/**
	 * 识别属性tagName的标签
	 * @param body
	 * @param tagName
	 * @return
	 */
	public static Elements getTagName(String body,String tagName){
		Document doc = Jsoup.parse(body);
		// 所有table的标签
		Elements elements = doc.select(tagName);
		// 返回第一个
		return elements;
	}
	/**
	 * 识别属性class的标签
	 * @param body
	 * @param classTag
	 * @return
	 */
	public static Elements getClassTag(String body, String classTag) {
		Document doc = Jsoup.parse(body);
		// 所有#id的标签
		return doc.select("." + classTag);
	}
	
	/**
	 * 获取tr标签元素组
	 * @param e
	 * @return
	 */
	public static Elements getTR(Element e) {
		return e.getElementsByTag("tr");
	}
	
	/**
	 * 获取td标签元素组
	 * @param e
	 * @return
	 */
	public static Elements getTD(Element e) {
		return e.getElementsByTag("td");
	}
	/**
	 * 获取表元组
	 * @param table
	 * @return
	 */
	public static List<List<String>> getTables(Element table){
		List<List<String>> data = new ArrayList<>();
		for (Element etr : table.select("tr")) {
			List<String> list = new ArrayList<>();
			for (Element etd : etr.select("td")) {
				String temp = etd.text();
				//增加一行中的一列
				list.add(temp);
			}
			//增加一行
			data.add(list);
		}
		return data;
	}
	/**
	 * 读html文件
	 * @param fileName
	 * @return
	 */
	public static String readHtml(String fileName){
		FileInputStream fis = null;
		StringBuffer sb = new StringBuffer();
		try {
			fis = new FileInputStream(fileName);
			byte[] bytes = new byte[1024];
			while (-1 != fis.read(bytes)) {
				sb.append(new String(bytes));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return sb.toString();
	}
	/**
	 * @Author 张双亮
	 * @Description $获取url列表
	 * @Param $param
	 * @return $return
	 **/
	
	public static List<Map<String,String>> getUrlList(String responseBody){
		Document doc = Jsoup.parse(responseBody);
		//获取表格
		List<Map<String,String>> list = new ArrayList<>();
		Elements tds = doc.getElementsByClass("blue");
		for(Element td : tds){
			String title = td.parent().parent().before("td").text();
			title = title.substring(0,title.indexOf("表")+1);
			String text = td.before("td").text();
			List<String> urls = td.select("a").eachAttr("href");
			String u = "";
			for(String url : urls){
				u += url + ",";
			}
			if(u != null && !"".equals(u)){
				u = u.substring(0,u.lastIndexOf(","));
			}
			Map<String,String> map = new HashMap<>();
			map.put("title",title+text);
			map.put("url",u);
			list.add(map);
		}
		return list;
	}
}

