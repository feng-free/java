package com.zsl.reflex;


import java.util.List;

/*
 * @ClassName Test
 * @Description 用于测试的方法类
 * @Author 张双亮
 * @Version 1.0
 **/
public class Test {
	
	
	public String getTest(){
		return "hello";
	}
	public Integer getTest1(int a, int b){
		return a + b;
	}
	public static String getTest2(){
		return " static hello";
	}
	public static Integer getTest3(int a, int b){
		return a * b;
	}
	public void getTest4(Demo demo){
		System.out.println(demo.getId()+"  "+demo.getName());
	}
	public void getTest5(List<String> list){
		for(String str : list){
			System.out.println(str);
		}
	}
	public void getTest6(List<Demo> list){
		for(Demo str : list){
			System.out.println(str);
		}
	}
	public void getTest7(String[] a){
		for(String str : a){
			System.out.println(str);
		}
	}
	public void getTest8(Demo[] demo){
		for(Demo str : demo){
			System.out.println(str);
		}
	}
	public void getTest9(int[] demo){
		for(int str : demo){
			System.out.println(str);
		}
	}
}
