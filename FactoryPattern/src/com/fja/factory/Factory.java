package com.fja.factory;
/**
 * 普通工厂模式：说白了普通工厂就是把工厂也抽象了
 * 优点：
 * 	相比于简单工厂,两者都实现了封装对象的创建过程,但是
 * 	1). 工厂方法模式先创建了一个框架,通过继承让子类决定如何实现。 
 * 		生产的产品不再由传入生产方法(produceMilk)的参数决定，而是由工厂决定
 * 	2). 工厂方法模式,比简单工厂更加灵活富有弹性,是简单工厂的进一步抽象。
 * 		新增一个产品也不需要去修改原有工厂的代码，只需要重新实例化一个新的工厂即可
 * 		
 * 
 * 缺点：
 * 	每增加一个产品(具体工厂)，都要相应的增加子工厂，增加额外的开发量。
 *  每个抽象出来的工厂只能生产一种产品。
 */
public class Factory {

	public static void main(String[] args) {
		//在客户端，用户选择哪种子类工厂就决定了生产哪种产品
		MilkFactory factory = new YiliFactory();
		Milk m = factory.produceMilk("hot");
		factory.aboard(m);
		m = factory.produceMilk("cold");
		factory.aboard(m);
	}
}

//工厂的超类
abstract class MilkFactory{
	//这里可以添加公共的逻辑，比如牛奶出厂的时候需要装车
	void aboard(Milk milk){
		System.out.println(milk.getBrand()+"牌牛奶装车。");
	}
	
	/**
	 * 问：工厂生产的方法，为什么是抽象方法：
	 * 答：因为需要不同的工厂去实现，不同工厂生产产品都是不同的，我们要将创建具体类的决定权交给子类，
	 * 	     因此这个方法需要是抽象方法，用于定制不同的需求的产品的生产。
	 */
	abstract Milk produceMilk(String type);
}

//具体的工厂
class YiliFactory extends MilkFactory{
	@Override
	Milk produceMilk(String type) {
		Milk m = null;
		if("hot".equals(type)){			//可以在生产方法中对产品进一步细分，不过说到底，还是只能生产牛奶
			m = new HotYili();
		}else{
			m = new ColdYili();
		}
		return m;
	}
}



//产品的接口
interface Milk{
	abstract String getBrand();
	
}

//具体产品(需要特定的工厂才能生产)
class ColdYili implements Milk{
	private String brand = "夏日冰镇--伊利";
	@Override
	public String getBrand() {
		return this.brand;
	}
}

class HotYili implements Milk{
	private String brand = "冬日温暖--伊利";
	@Override
	public String getBrand() {
		return this.brand;
	}
}

//具体产品
class Mengniu implements Milk{
	private String brand = "蒙牛";
	@Override
	public String getBrand() {
		return this.brand;
	}
}