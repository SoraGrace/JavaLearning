package com.fja.innerClass;
/**
 * 局部内部类： 
 * 【注意】
 * 如果局部内部类访问了一个局部变量，那么该局部变量必须使用final修饰
 * 
 * 原因：
 * 	当test()方法执行完毕之后，局部变量str占用的内存会被马上释放，然而此时_Inner对象的生命周期
 *  还没有结束，并且_Inner对象的print()方法还访问着str变量，这显然与局部变量的定义相违背，因此sun公司
 *  的解决方法是当一个局部内部类访问一个局部变量的时候，将这个局部变量复制一份到内存中，局部内部类访问的就
 *  是这个复制品。而为了保证局部变量和局部变量复制品的数据一致，就必须将局部变量定义成常量，禁止修改。
 */
public class Anonymous {

	public static void main(String[] args) {
		new _Outer().test();
		
		//创建匿名内部类对象
		new Animal(){
			protected void run() {
				System.out.println("狗在跑");
			}

			@Override
			protected void eat() {
				System.out.println("狗在吃");
			}
		}.run();
		
		//如果要多次调用匿名内部类的方法,可以使用多态
		Animal dog = new Animal(){
			@Override
			protected void run() {
				System.out.println("狗在跑");
			}

			@Override
			protected void eat() {
				System.out.println("狗在吃");
			}
			//子类独有方法是不能被多态调用的，因此不能使用这种方式调用匿名内部类特有的方法,因此当内部类里面有子类的特有的方法的时候还是使用局部内部类。
			public void bark() {
				System.out.println("狗在叫");
			}
		};
		
		dog.eat();
		dog.run();
		/*
		 无法通过编译
		dog.bark();
		 */
	}
}


class _Outer{
	public void test(){
		final String str = "局部内部类访问的局部变量";
		class _Inner{
			int x = 10;
			public void print(){
				System.out.println("_Inner类的打印方法");
				System.out.println(str);
			}
		}
		//局部内部类只能在声明的方法内部创建对象
		_Inner inner = new _Inner();
		inner.print();
	}
}

/**
 * 匿名内部类：没有类名的局部内部类
 * 优点：能简化书写
 * 匿名内部类使用的前提：必须存在继承或者实现关系 
 */
 
abstract class Animal{
	protected abstract void run();
	protected abstract void eat();
}

