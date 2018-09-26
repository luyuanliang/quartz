package org.web.quartz.utils;

import org.web.quartz.domain.AppJobEnum;

public class EnumHelper {

	public static boolean checkExist(Class<?> e, String arg) {
		Object [] array = e.getEnumConstants();
		for(int i=0;i<array.length;i++){
			if(array[i].toString().equalsIgnoreCase(arg)){
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		System.out.println(checkExist(AppJobEnum.STATUS.class, "PASUSE"));
	}
}
