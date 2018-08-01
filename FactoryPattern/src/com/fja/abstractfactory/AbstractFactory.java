package com.fja.abstractfactory;
/**
 * 抽象工厂：
 * 在一种普通工厂中，用户只能生产一种商品,而抽象工厂中引入了产品族的概念。抽象工厂可以生产多个产品。
 * 在这里，我们设计的伊利和蒙牛工厂已经不满足于生产牛奶这一种产品了，还要加入雪糕产品的生产。 
 * 产品族：伊利是一个品牌，它旗下的产品的集合就是一个产品族，同理蒙牛也是一样
 * 
 * 优点：
 * 	1). 可以生产多种产品(一个产品族)
 * 缺点：
 *  1). 拓展产品族很困难，比如我在产品族新增一个冰淇淋(ice-cream),则需要修改抽象工厂和各个具体工厂。
 *  	但是新加一个品牌却很简单，比如我加入佑康食品。 
 */
public class AbstractFactory {

	public static void main(String[] args) {
		Factory factory = new YiliFactory();
		System.out.println(factory.produceIce().getBrand());
		System.out.println(factory.produceMilk().getBrand());
	}
}
//抽象工厂
abstract class Factory{
	abstract IMilk produceMilk();
	abstract Iice produceIce();
}


//具体工厂
class YiliFactory extends Factory{			//生产伊利产品族，伊利雪糕和伊利牛奶是伊利的一套产品

	@Override
	IMilk produceMilk() {
		return new Yili_Milk();
	}

	@Override
	Iice produceIce() {
		return new Yili_IceLolly();
	}	
}


class MengniuFactory extends Factory{		//生产蒙牛产品族

	@Override
	IMilk produceMilk() {
		return new Mengniu_Milk();
	}

	@Override
	Iice produceIce() {
		return new Mengniu_IceLolly();
	}	
}

interface Iice{
	String getBrand();
}

interface IMilk{
	String getBrand();
}

//四种产品

//伊利牌冰棍
class Yili_IceLolly implements Iice{
	private String brand = "伊利雪糕"; 
	@Override
	public String getBrand() {
		return brand;
	}
}

//蒙牛牌冰棍
class Mengniu_IceLolly implements Iice{
	private String brand = "蒙牛雪糕"; 
	@Override
	public String getBrand() {
		return brand;
	}
}

//伊利牌牛奶
class Yili_Milk implements IMilk{
	private String brand = "伊利牛奶"; 
	@Override
	public String getBrand() {
		return brand;
	}
}

//蒙牛牌牛奶
class Mengniu_Milk implements IMilk{
	private String brand = "蒙牛牛奶"; 
	@Override
	public String getBrand() {
		return brand;
	}
}