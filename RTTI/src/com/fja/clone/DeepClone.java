package com.fja.clone;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象的深克隆：
 * 	对象的深克隆就是利用对象的输入输出流把对象写到文件上，然后读取对象的信息。
 * 	这个过程就被称为对象的深克隆
 * 
 */
public class DeepClone {

	public static void main(String[] args) {
		_Person _p = new _Person("铁蛋",new _Address("中国","铁山坪"));
		serializer(_p);
		_Person _cloneObj = deSerializer(_Person.class);
		System.out.println(_p);				//铁蛋===铁山坪
		System.out.println(_cloneObj);		//铁蛋===铁山坪
		_cloneObj.name = "喜羊羊";
		_cloneObj.address.city = "羊村";
		System.out.println(_p);				//铁蛋===铁山坪
		System.out.println(_cloneObj);		//喜羊羊===羊村
		//深克隆成功，_Person的成员变量_Address的对象也被克隆了一份
	}
	//将对象系列化到硬盘中去
	public static <T> void serializer(T t){
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("E:/CloneObj.txt"));
			oos.writeObject(t);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(oos!=null)oos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static <T> T deSerializer(Class<T> clazz){
		ObjectInputStream ois = null;
		T t = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("E:/CloneObj.txt"));
			t = (T)ois.readObject();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return t;
	}
}

class _Person implements Serializable{
	String name;
	_Address address;
	public _Person(String name,_Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return this.name+"==="+this.address.city;
	}
}

//【注意】_Address也要实现Serializable接口
class _Address implements Serializable{
	String country;
	String city;
	public _Address(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}
}