package com.fja.innerClass;
/**
 * 内部类的类别：
 * 	1. 成员内部类
 * 		成员内部类的访问方式
 * 		方式一: 在外部类提供一个方法创建内部类的对象进行访问
 * 		方式二: 外部类.内部类 变量名 = new 外部类().new 内部类();
 * 	2. 局部内部类 
 * 
 * 内部类的class文件名：外部类名$内部类名
 * 
 * 内部类的优点：
 * 	内部类可以直接访问外部类的所有成员
 * 
 * 【注意】
 * 	1.如果外部类与内部类存在同名的成员变量的时候，在内部类中默认的情况下是访问内部类的成员变量
 * 	
 * 	2.如果外部类与内部类存在同名的成员变量的时候，可以通过外部类.this.成员变量名指定访问外部类的成员变量
 * 	
 *  3.私有的成员内部类只能在外部类提供一个方法创建内部类的对象进行访问，不能在其他类创建内部类的对象
 * 	
 *  4.成员内部类一旦出现了静态的成员，那么内部类也必须使用static修饰
 * 		原因： 静态数据是不需要依赖对象的，但是内部类有静态成员但是本身不是静态的内部类的时候，访问该静态成员变量就需要
 * 			  外部类的对象==>new Outer().Inner.静态成员内部类。违反了静态类不依赖对象的原则
 */
public class InnerClass {

	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.instance().print();
		
		Outer.Inner inner = new Outer().new Inner();
		inner.print();
	}
}


class Outer{
	
	int i = 20;			//成员变量
	int x = 100;
	//成员内部类
	class Inner{
		int i = 10;
		
		public void print(){
			System.out.println("这是成员内部类Inner");
			//内部类可以直接访问外部类的所有成员
			System.out.println("外部类的成员变量x： "+x);
			//变量重名的情况下，默认访问内部类的成员变量
			System.out.println("重名的变量i： "+i);
			//变量重名的情况下，访问外部类的成员变量需要用到外部类.this
			System.out.println("外部类的重名的变量i： "+Outer.this.i);
		}
	}
	
	public Inner instance(){
		return new Inner();
	}
}