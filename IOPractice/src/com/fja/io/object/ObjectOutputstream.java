package com.fja.io.object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象输出流
 * 主要的作用是将对象进行序列化，一旦写到文件上就可以对文件进行持久化
 * 
 * 注意的细节：
 * 1. 对象输出流本身也是没有读写能力的
 * 2. 如果对象需要被写出到文件上，那么对象所属的类必须要实现Serializable接口
 * 3. 所有的集合都实现了Serializble接口
 * 4. 类中那个属性不想被序列化，就用transient修饰。
 * 5. 被序列化的对象中的成员变量如果是对象也需要实现serializable接口
 */
public class ObjectOutputstream {

	public static void main(String[] args) {
		writeObject();
	}
	
	//将对象持久化到对象上
	public static void writeObject(){
		Person p = new Person("铁蛋",1,5000.0,new Address("中国","北京"));
		System.out.println(p);
		ObjectOutputStream oos = null;
		try{
			FileOutputStream fos = new FileOutputStream("E:/obj.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(oos!=null)oos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
}


/**
 * 注意，被ObjectOutputStream写出到流中的对象必须要实现Serializable接口
 * Serializable接口中不包含任何的方法，是一个标志接口，标志着实现类的对象可以被序列化
 */
class Person implements Serializable{
	/**
	 * 有了serialVersionUID，jvm就不会再给class文件计算serialVersionUID了。
	 * 防止因为改变类而导致反序列化失败
	 */
	private static final long serialVersionUID = 1L;
	
	String name;
	
	int age;
	
	transient double salary;			//不想被序列化的成员变量
	
	Address address;
	
	public Person(String name, int age,double salary,Address address) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "姓名： "+this.name+"  年龄： "+this.age+"  薪水： "+this.salary+"  地址："+this.address.country;
	}
}

//被序列化的对象中的成员变量如果是对象也需要实现serializable接口
class Address implements Serializable{
	String country;
	String city;
	public Address(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}
}