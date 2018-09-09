package com.fja.clone;
/**
 * 克隆：
 * 对象的浅克隆
 * 注意的细节：
 * 	1.如果一个对象需要调用clone的方法，那么该对象所属的类必须要实现Cloneable接口
 * 	2.Cloneable接口是一个标识接口
 * 	3.对象的浅克隆就是克隆一个对象的时候，如果被克隆的对象中维护了另外一个对象，这时候只是克隆另外一个对象的内存地址，
 * 	     而并没有将另外的对象也克隆一份。
 * 	4.浅克隆虽然产生了新的对象，但是没有调用其类的构造方法
 * 
 * 对象的深克隆
 * 
 * 【注意】clone()是java.lang.Object的方法，修饰符是protected
 * 		因此除非下面代码中Clone是Person的父类才行，或者Person类重写Clone方法（在同个包内）
 */
public class ShallowClone{
	public static void main(String[] args) {
		Person ironEgg = new Person("铁蛋",new Address("中国","铁岭"));
		Person cloneObj = null;
		try {
			//【注意】Object类本身不实现Cloneable接口，因此这里Person类没有实现Cloneable接口，就会报CloneNotSupportedException
			cloneObj = (Person)ironEgg.clone();
			System.out.println(ironEgg);
			System.out.println(cloneObj);
			//修改克隆对象成员变量的属性
			cloneObj.name = "牛二";
			cloneObj.address.city = "牛家村";
			System.out.println(ironEgg);
			System.out.println(cloneObj);
			//证明浅克隆，克隆对象成员变量也是对象的话，只复制内存地址。
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}

//Cloneable接口和Serializable接口一样是标识接口，没有任何抽象方法
class Person implements Cloneable{
	String name;
	Address address;
	public Person(String name,Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return this.name+"==="+this.address.city;
	}
	
	//因为调用Person类对象的方法的类不是Person的父类，这里就重写Clone方法
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class Address{
	String country;
	String city;
	public Address(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}
}