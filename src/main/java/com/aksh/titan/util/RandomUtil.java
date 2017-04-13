package com.aksh.titan.util;

public class RandomUtil {

	static String[] arr=new String[]{"SALES","DEVELOPMENT","PS","HIRING","QA","MARKETING"};
	private static int medianAge=40;
	private static int DEVIATION=15;
	public static int  getAge(){
		int age=medianAge;
		int deviation=(int)Math.round(Math.random()*DEVIATION);
		return age+getSign()*deviation;
	}
	
	public static String getDepartment(){
		int index=Integer.valueOf(Math.round(Math.random()*(arr.length-1))+"");
		return arr[index];
	}
	public static int getSign(){
		double sign=0.5-Math.random();
		if(sign>0){
			return 1;
		}else{
			return -1;
		}
	}
}
