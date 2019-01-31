package com.zsl.reptile;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPUtils {
	
	public static String getHtmlbody(String url){
		String htmlBody = "";
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			htmlBody =  new String(responseBody);
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return htmlBody;
	}
	/**
	 * @Author 张双亮
	 * @Description 去除html中的style
	 * @Param $param
	 * @return $return
	 **/
	public static String ReplaceSpace(String content) {
		String regEx = "x:num=\"(.*?)\"";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		String okContent = null;
		if (m.find()) {
			okContent = m.replaceAll("");
		}
		String regEx1 = "x:str=\"(.*?)\"";
		Pattern p1 = Pattern.compile(regEx1);
		Matcher m1 = p1.matcher(okContent);
		String okContent1 = null;
		if (m1.find()) {
			okContent1 = m1.replaceAll("");
		}
		okContent1 = okContent1.replace("<col>","<col/>");
		return okContent1;
	}
}