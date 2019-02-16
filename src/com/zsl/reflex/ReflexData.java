package com.zsl.reflex;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.*;

/*
 * @ClassName ReflexData
 * @Description 利用发射获取数据对象，并实例化
 * @Author 张双亮
 * @Version 1.0
 **/
public class ReflexData {
	
	/**
	 * @Author 张双亮
	 * @Description 通过反射得到相应的实体对象并获取属性值
	 * @Param $param
	 * @return $return
	 **/
	public static List<Map<String,Object>> getObjFieldByReflex(List<? extends Object> list){
		List<Map<String,Object>> mapList = new ArrayList<>();
		for(Object obj : list){
			mapList.add(getObjFieldByReflex(obj));
		}
		return mapList;
	}
	/**
	 * @Author 张双亮
	 * @Description 通过反射得到相应的实体对象并获取属性值
	 * @Param $param
	 * @return $return
	 **/
	public static Map<String,Object> getObjFieldByReflex(Object obj){
		Map<String,Object> map = new HashMap<>();
		try{
			Class userCla = obj.getClass();//获取类对象
			Field[] fs = userCla.getDeclaredFields();//获取对象实例的属性
			for(int i = 0;i < fs.length;i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(obj);// 得到此属性的值
				map.put(f.getName(),val);
				System.out.println("name:" + f.getName() + "\t value = " + val);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Author 张双亮
	 * @Description 通过类型，方法名，参数列表（非对象数组）调用其方法
	 * @Param $param
	 * @return $return
	 **/
	public static Object getObjMethodByReflex(String className, String methodName, Object ... obj){
		Object objResult = null;
		try{
			String[] strs = getParamName(className,methodName);
			Class<?>[] classMethods = new Class<?>[strs.length];
			for(int i = 0; i < strs.length; i++){
					Class<?> c = getParamsClassTypeByName(strs[i]);
					if(c == null){
						classMethods[i] = obj[i].getClass();
					}else{
						classMethods[i] = c;
					}
			}
			Class<?> clazz = Class.forName(className);
			Method objMethods = clazz.getMethod(methodName,classMethods);
			objResult = objMethods.invoke(clazz.newInstance(),obj);
		}catch (Exception e){
			e.printStackTrace();
		}
		return objResult;
	}
	/**
	 * @Author 张双亮
	 * @Description 通过类型，方法名，参数列表（对象数组）调用其方法
	 * @Param $param
	 * @return $return
	 **/
	public static Object getObjMethodsByReflex(String className, String methodName, List list){
		Object objResult = null;
		try{
			Object[] objects = new Object[list.size()];
			String[] strs = getParamName(className,methodName);
			Class<?>[] classMethods = new Class<?>[strs.length];
			for(int i = 0; i < strs.length; i++){
				if(strs[i].contains("[]")){
					classMethods[i] = list.get(i).getClass();
				}else{
					Class<?> c = getParamsClassTypeByName(strs[i]);
					if(c == null){
						classMethods[i] = list.get(i).getClass();
					}else{
						classMethods[i] = c;
					}
				}
				objects[i] = list.get(i);
			}
			Class<?> clazz = Class.forName(className);
			Method objMethods = clazz.getMethod(methodName,classMethods);
			objResult = objMethods.invoke(clazz.newInstance(),objects);
		}catch (Exception e){
			e.printStackTrace();
		}
		return objResult;
	}
	/**
	 * @Author 张双亮
	 * @Description 通过参数类型的名字得到参数类型
	 * @Param $param
	 * @return $return
	 **/
	public static Class<?> getParamsClassTypeByName(String paramsClassName){
		Class<?> clazz = null;
		if(paramsClassName == null || "".equals(paramsClassName)){
			return clazz;
		}
		int index = paramsClassName.indexOf("<");//判断是否有泛型
		String name = "";
		if(index == -1){
			name = paramsClassName.substring(paramsClassName.lastIndexOf(".")+1);
		}else{
			name = paramsClassName.substring(0,index);
		}
		if(name.contains("List")){
			name = "List";
		}else if(name.contains("Set")){
			name = "Set";
		}else if(name.contains("Map")){
			name = "Map";
		}
		try{
			switch (name) {
				case "int":
				case "Integer":
					clazz = int.class;
					break;
				case "String":
					clazz = String.class;
					break;
				case "double":
				case "Double":
					clazz = double.class;
					break;
				case "boolean":
				case "Boolean":
					clazz = boolean.class;
					break;
				case "char":
				case "Character":
					clazz = char.class;
					break;
				case "byte":
				case "Byte":
					clazz = byte.class;
					break;
				case "short":
				case "Short":
					clazz = short.class;
					break;
				case "long":
				case "Long":
					clazz = long.class;
					break;
				case "float":
				case "Float":
					clazz = float.class;
					break;
				case "List":
					clazz = List.class;
					break;
				case "Set":
					clazz = Set.class;
					break;
				case "Map":
					clazz = Map.class;
					break;
				case "String[]":
					clazz = String[].class;
					break;
				case "StringBuffer":
					clazz = StringBuffer.class;
					break;
				case "StringBuilder":
					clazz = StringBuilder.class;
					break;
				default:
					clazz = null;
					break;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return clazz;
	}
	/**
	 * @Author 张双亮
	 * @Description 通过类型和方法名得到参数类型名字
	 * @Param $param
	 * @return $return
	 **/
	public static String[] getParamName(String className, String methodName){
		String[] str = null;
		try{
			Class<?> clazz = Class.forName(className);
			Method[] methods = clazz.getMethods();
			for(Method method : methods){
				if(method.getName().equals(methodName)){
					Type[] typeVariables = method.getGenericParameterTypes();
					str = new String[typeVariables.length];
					for(int i = 0; i < typeVariables.length; i++){
						str[i] = typeVariables[i].getTypeName();
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return str;
	}
}
