package com.fja.simple;
/**
 * 简单工厂进阶：
 * 利用反射增加工厂的拓展性 
 */
public class FactoryAdvance {
	//客户端
	public static void main(String[] args) {
		ReflectFactory factory = new ReflectFactory();
		
		$Market market = new $Market(factory);
		
		market.sellMilk(Yili.class);
		market.sellMilk(Mengniu.class);
	}
}

/**
 * 在简单工厂中，有一个问题，当新增一个产品的时候需要去修改工厂类的生产代码，增加if else的分支
 * 在利用反射之后，可以弥补简单工厂的拓展性,新增产品的时候不需要去修改工厂的代码,从而在某种程度上实现"开放-关闭"原则
 */
class ReflectFactory{
	
	public Milk produceMilk(Class<?> clazz){
		Milk m = null;
		try {
			m = (Milk)Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException e) {
			System.out.println("不支持抽象类和接口");
		} catch (IllegalAccessException e) {
			System.out.println("没有权限，不能访问私有构造器");
		} catch (ClassNotFoundException e) {
			System.out.println("类不存在");
		}
		
		return m;
	}
}

class $Market {
	
	private ReflectFactory factory;
	
	public $Market(ReflectFactory factory){
		this.factory = factory;
	}
	
	public void sellMilk(Class<?> clazz){
		Milk milk = factory.produceMilk(clazz);
		if(milk!=null)milk.sellMilk();
	}
}