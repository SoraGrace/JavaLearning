package com.fja.observer;
//想要订阅天气预报就必须实现天气预报用户接口
public interface IWeatherUser {
	
	public void react(String weather);
	
}
