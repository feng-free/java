package com.zsl.conDiagram;
/*
 * @ClassName ConnDiagram
 * @Description 连接图拼接
 * @Author 张双亮
 * @Version 1.0
 **/

public class ConnDiagram {
	
	
	public static void main(String[] args) {
		String gram = "s--1:2--3--4:5--t,1--4,2--5";
		for(int i = 1; i <= 5; i++){
			System.out.println(""+i+"去掉后："+getConnDiagram(""+i+"",gram));
		}
	}
	
	/**
	 * @Author 张双亮
	 * @Description 通过删除某一结点获取删除节点后的连接图
	 * @Param $param
	 * @return $return
	 **/
	private static String getConnDiagram(String v,String gram){
		String str = "";
		boolean b = isNotNull(v,gram);
		if(!b){
			System.out.println("参数不能为空");
			return "";
		}
		String[] strs = gram.split(",");
		for(int i = 0; i < strs.length; i++){
			if(strs[i] != null && !"".equals(strs[i])){
				String s = "";
				if(strs[i].contains(v)){
					s = getTGram(strs[i].replaceAll(v,""),"--");
				}else{
					s = strs[i];
				}
				str += s;
			}
			str += ",";
		}
		str = str.substring(0,str.lastIndexOf(","));
		return str;
	}
	/**
	 * @Author 张双亮
	 * @Description $通过匹配符得到正确的连接图
	 * @Param $param
	 * @return $return
	 **/
	private static String getTGram(String str, String ms){
		String st = "";
		boolean b = isNotNull(str,ms);
		if(!b){
			System.out.println("参数不能为空");
			return "";
		}
		String[] s = str.split(ms);
		for(int i = 0; i < s.length; i++){
			if(!"".equals(s[i])){
				st += s[i] + ms;
			}
			
		}
		st = st.substring(0,st.lastIndexOf(ms));
		st = st.replace("--:","--");
		st = st.replace(":--","--");
		return st;
	}
	/**
	 * @Author 张双亮
	 * @Description 判断参数是否为null或者空
	 * @Param $param
	 * @return $return
	 **/
	private static boolean isNotNull(String ... str){
		boolean b = true;
		if(str == null || "".equals(str)){
			b = false;
		}
		b = true;
		for(int i = 0; i < str.length; i++){
			if(str == null || "".equals(str)){
				b = false;
			}
		}
		return b;
	}
}
