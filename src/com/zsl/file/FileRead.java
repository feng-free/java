package com.zsl.file;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
/*
 * @ClassName FileRead
 * @Description 文件读取
 * @Author 张双亮
 * @Version 1.0
 **/
public class FileRead {
	
	/**
	 * @Author 张双亮
	 * @Description 获取文件中的数据
	 * @Param $param
	 * @return $return
	 **/
	
	public Set<String> readData(String path){
		Set<String> set = new HashSet<>();
		BufferedReader br = null;
		try{
			File file = new File(path);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
			br = new BufferedReader(isr);
			String lineTxt = "";
			while ((lineTxt = br.readLine()) != null) {
				set.add(lineTxt);
			}
		}catch (Exception e){
			System.out.println("文件读取失败");
			e.printStackTrace();
		}finally {
			try {
				br.close();
			}catch (Exception e){
				System.out.println("文件读取流关闭失败");
				e.printStackTrace();
			}
		}
		return set;
	}
	/**
	 * @Author 张双亮
	 * @Description 将结果写入文件
	 * @Param $param
	 * @return $return
	 **/
	
	public void writeData(String path,Set<String> set){
		BufferedWriter bw = null;
		try{
			File file = new File(path);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file));
			bw = new BufferedWriter(osw);
			for(String text : set){
				bw.write(text);
			}
		}catch (Exception e){
			System.out.println("文件写入失败");
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			}catch (Exception e){
				System.out.println("文件写入流关闭失败");
				e.printStackTrace();
			}
		}
	}
}
