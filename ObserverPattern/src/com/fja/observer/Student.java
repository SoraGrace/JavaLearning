package com.fja.observer;

public class Student implements IWeatherUser{
	String name;

	public Student(String name) {
		super();
		this.name = name;
	}
	
	//做出对不同天气的应对
	public void react(String weather){
		if("晴天".equals(weather)){
			System.out.println(this.name+"开开心心去上学");
		}else if("下雨".equals(weather)){
			System.out.println(this.name+"带伞去上学");
		}else if("台风".equals(weather)){
			System.out.println(this.name+"在寝室睡觉");
		}else if("冰雹".equals(weather)){
			System.out.println(this.name+"在寝室打游戏");
		}else if("下雪".equals(weather)){
			System.out.println(this.name+"外出玩雪");
		}
	}
}
