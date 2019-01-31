package com.zsl.tablehash;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/*
 * @ClassName TableHash
 * @Description 通过hash值获取表名
 * @Author 张双亮
 * @Version 1.0
 **/
public class TableHash {
	
	
	/*private static final BigDecimal FNV_PRIME_32 = 2;
	private static final BigDecimal FNV_PRIME_64 = 2^40 + 2^8 + 0xb3 ;
	private static final BigDecimal OFFSET_BASIS_32 = 2166136261;
	private static final BigDecimal OFFSET_BASIS_64 = 14695981039346656037;*/
	/**
	 * @Author 张双亮
	 * @Description 通过hash值获取表名，并将的hash放到相应的数据库名中
	 * @Param $param
	 * @return $return
	 **/
	public Map<String,String> getTableNameByHash(Set<String> set){
		Map<String,String> map = new HashMap<>();
		for(String value : set){
			int num = Integer.parseInt(value.substring(0,2)) % 32;
			String index = "";
			if(num < 10){
				index = "0" + num;
			}
			String tableName = "md5_" + index;
			String md5 = map.get(tableName);
			if(md5 != null && !"".equals(md5)){
				md5 = md5 + value +",";
				map.put(tableName,md5);
			}else{
				map.put(tableName,value + ",");
			}
		}
		return map;
	}
	
	
}
