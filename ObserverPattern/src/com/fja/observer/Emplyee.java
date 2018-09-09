package com.fja.observer;

public class Emplyee implements IWeatherUser{
	
	String name;

	public Emplyee(String name) {
		super();
		this.name = name;
	}
	
	//做出对不同天气的应对
	public void react(String weather){
		if("晴天".equals(weather)){
			System.out.println(this.name+"外出郊游");
		}else if("下雨".equals(weather)){
			System.out.println(this.name+"出门带伞");
		}else if("台风".equals(weather)){
			System.out.println(this.name+"出门上班");
		}else if("冰雹".equals(weather)){
			System.out.println(this.name+"在家休息");
		}else if("下雪".equals(weather)){
			System.out.println(this.name+"外出玩雪");
		}
	}
}
