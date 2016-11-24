package com.hrms.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {
	
	//动过反射获得父类泛型类型
	public static Class getSuperClassGenericType(Class clazz, int index){
		//getGenericSuperclass()获得带有泛型的父类
		Type genType = clazz.getGenericSuperclass();
		
		if(!(genType instanceof ParameterizedType)){
			return Object.class;			
		}
		
		//ParameterizedType参数化类型，即泛型
		//getActualTypeArguments获取参数化类型的数组，泛型可能有多个
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		
		if(index >= params.length || index < 0){
			return Object.class;
		}
		
		if(!(params[index] instanceof Class)){
			return Object.class;
		}
			
		return (Class) params[index];	
	}
	
	//动过反射获得父类泛型类型
	public static Class getSuperClassGenericType(Class clazz){
		return getSuperClassGenericType(clazz, 0);
	}
}
