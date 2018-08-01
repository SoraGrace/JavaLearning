package com.fja.simple;
/**
 * 不使用工厂模式的传统的做法 
 */
public class WithouFactory {
	//客户端
	public static void main(String[] args) {
		_Market market = new _Market();
		market.sellMilk("特仑苏");
		market.sellMilk("伊利");
	}
}

/**
 * 不使用工厂的缺点：
 * 新增牛奶的种类需要不停地维护sellMilk()方法，不符合开闭原则
 * 耦合性太大，对Milk的实现类对象依赖，产品是低层组件，不应该过分依赖
 * 
 */
class _Market{
	private Milk m = null;
	public void sellMilk(String brand){
		 if("伊利".equals(brand)){
			 m = new Yili();
		 }else if("蒙牛".equals(brand)){
			 m = new Mengniu();
		 }else{
			 System.out.println("无法生产"+brand+"牌牛奶");
		 }
		 if(m!=null)m.sellMilk();
	}
}
