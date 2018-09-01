package com.fja.io.object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 对象输入流：
 * 把文件，内存中的信息读取出来就是反序列化 
 * 
 * 注意：
 * 对象输入流本身也是没有读写能力的
 */
public class ObjectInputstream {

	public static void main(String[] args) {
		readObject();		//姓名： 铁蛋 年龄： 1
	}
	
	public static void readObject(){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("E:/obj.txt"));
			Object obj = ois.readObject();
			System.out.println((Person)obj);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
