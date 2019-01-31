package com.zsl.reptile;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/*
 * @ClassName ReptileData
 * @Description 爬虫数据
 * @Author 张双亮
 * @Version 1.0
 **/
public class ReptileData {
	
	public static void main(String[] args) {
		String URL = "http://www.customs.gov.cn/customs/302249/302274/302277/index.html";
		String htmlBody = HTTPUtils.getHtmlbody(URL);
		List<Map<String,String>> list = HtmlParser.getUrlList(htmlBody);
		int index = 0;
		for(Map map : list) {
			if (String.valueOf(map.get("url")).contains("http")) {
				if (index >= 1) {
					break;
				}
				String childHtmlBody = HTTPUtils.getHtmlbody(String.valueOf(map.get("url")));
				Elements table = HtmlParser.getTagName(childHtmlBody, "table");
				if(table != null){
					String htmlTable = HTTPUtils.ReplaceSpace(table.outerHtml());
					System.out.println(htmlTable);
					HSSFWorkbook wb = ConvertHtml2Excel.table2Excel(htmlTable);
					File file = new File("D:\\"+String.valueOf(map.get("title"))+".xls");
					try{
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						wb.write(fileOutputStream);
						fileOutputStream.close();
						index++;
					}catch(Exception fileError){
						fileError.printStackTrace();
					}
				}
			}
		}
	}
}
