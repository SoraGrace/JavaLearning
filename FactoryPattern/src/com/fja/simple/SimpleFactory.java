package com.fja.simple;
/**
 * 简单工厂：
 *  
 */
public class SimpleFactory {
	/**
	 * 这里是客户端，获得产品并且卖出
	 * 这里可以看出客户端不需要控制产品(牛奶)的实例化， 去除了客户端与具体产品的依赖,
	 * 封装了对象的创建过程，只需要依赖Market和SimpleFactory就可以了。
	 * 对比于WithoutFactory的客户端，当新增产品的时候，Market和客户端的代码不需要修改
	 */
	public static void main(String[] args) {
		
		MilkFactory factory = new MilkFactory();
		Market market = new Market(factory);
		market.sellMilk("特仑苏");
		market.sellMilk("伊利");
	}
}

/**
 * 缺点：
 * 当新增产品时需要修改工厂的代码,增加if else的判断
 *  
 */
class MilkFactory{
	IMilk produceMilk(String brand){
		IMilk m = null;
		if("蒙牛".equals(brand)){
			m = new Mengniu();
		}else if("伊利".equals(brand)){
			m = new Yili();
		}else{
			System.out.println("无法生产"+brand+"牌牛奶");
		}
		return m;
	}
}

//定义一个接口，限定产品的类型
interface IMilk {
	public abstract String getBrand();
	
	public abstract void sellMilk();
}

//具体的产品
class Yili implements IMilk{
	private String brand = "伊利";
	
	public String getBrand(){
		return this.brand;
	}
	@Override
	public void sellMilk() {
		System.out.println("售出"+this.brand);
	}
}

//具体的产品
class Mengniu implements IMilk{
	private String brand = "蒙牛";

	@Override
	public String getBrand() {
		// TODO Auto-generated method stub
		return this.brand;
	}

	@Override
	public void sellMilk() {
		System.out.println("售出"+this.brand);
	}
}


class Market {
	
	private MilkFactory factory;
	
	public Market(MilkFactory factory){
		this.factory = factory;
	}
	
	public void sellMilk(String brand){
		IMilk milk = factory.produceMilk(brand);
		if(milk!=null)milk.sellMilk();
	}
}