package com.fja.io.object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 对象输入流：
 * 把文件，内存中的信息读取出来就是反序列化 
 * 
 * 注意：
 * 1. 对象输入流本身也是没有读写能力的
 * 
 * 2. 对象反序列化的时候会依赖本地的class文件，serialVersionUID是用于记录class文件信息，是这个ID
 * 	      是通过一个类的类名、成员、包名、工程名计算出来的。
 * 
 * 3. 将对象序列化之后，又对类进行了修改，之后反序列化会出现异常，因为流中、文件中的对象的serialVersionUID
 * 	      和本地的class文件的serialVersionUID冲突了，反序列化失败。修改类方法不会对serialVersionUID造成变化。
 * 
 * 4. 如果序列化与反序列化之间可能会修改类的成员，那么为了防止出现上述的serialVersionUID冲突异常，需要给类指定一个
 *    serialVersionUID。有了serialVersionUID，jvm就不会给class文件计算serialVersionUID了。
 */
public class ObjectInputstream {

	public static void main(String[] args) {
		readObject();		//姓名： 铁蛋  年龄： 1  薪水： 0.0  地址：中国
	}
	
	public static void readObject(){
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("E:/obj.txt"));
			/**
			 * 问：对象的反序列化有对象产生，产生对象的时候会调用构造函数么？
			 * 答：反序列化创造对象时，不会调用构造方法 
			 */
			Object obj = ois.readObject();
			System.out.println((Person)obj);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
