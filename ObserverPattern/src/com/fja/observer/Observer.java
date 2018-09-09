package com.fja.observer;

import java.util.ArrayList;
import java.util.Random;

/**
 * 观察者设计模式：
 * 观察者设计模式解决的问题是当一个对象发生指定的动作的时，要通知另外一个对象做出相应的处理
 * 
 * 步骤：
 * 	1. 当目前对象（WeatherStation）发生制定动作时，要通知另外一个对象（Student和Empolyee）做出相应的处理，这时候应该把对方的相应处理方法（react）定义到接口(IWeather)上。
 * 	2. 在当前对象维护借口的引用（ArrayList<IWeatherUser>），当发生制定的动作即可利用多台调用接口中的方法
 */
public class Observer {

	public static void main(String[] args) {
		WeatherStation station = new WeatherStation();
		Emplyee emp = new Emplyee("小明");
		Student stu = new Student("狗娃");
		station.addUser(emp);
		station.addUser(stu);
		
		station.startWork();
	}
}

class WeatherStation{
	String[] weathers = {"晴天","台风","下雨","冰雹","下雪"};
	//当前天气
	String currentWeather;
	
	//订阅天气预报的人，【注意】在这里用了接口事实上是降低了对特定类的依赖，降低了耦合性
	ArrayList<IWeatherUser> list = new ArrayList<IWeatherUser>();
	
	//更新天气
	public void updateWeather(){
		Random random = new Random();
		
		//随机产生一个索引值
		int index = random.nextInt(weathers.length);
		
		currentWeather = weathers[index];
		
		System.out.println("当前天气是"+this.currentWeather);
		
		//通知所有预定天气预报的用户
		for(IWeatherUser user:list){
			user.react(currentWeather);
		}
	}
	
	//开始工作
	public void startWork(){
		final Random random = new Random();
		new Thread(){
			public void run() {
				while(true){		//每1-1.5秒更新天气
					try {
						updateWeather();
						int interval = random.nextInt(501)+1000;	//包头不包尾
						Thread.sleep(interval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	//添加订阅天气预报的用户
	public void addUser(IWeatherUser user){
		list.add(user);
	}
}
