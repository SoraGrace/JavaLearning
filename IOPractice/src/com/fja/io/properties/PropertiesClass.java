package com.fja.io.properties;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * Properties类(配置文件类)属于集合(map)体系,本身就是一个集合
 * Properties继承了Hashtable 
 * 
 * 细节：
 * 1. 如果配置文件的信息，一旦使用了中文，那么在使用store方法生成配置文件的时候，只能使用字符流
 * 2. 如果Properties对象中的内容发生了变化，一定要重新使用store方法存储到文件中去，否则配置文件信息不会发生变化。
 */
public class PropertiesClass {
	public static void main(String[] args) {
		baseProcess();
	}
	//基本使用：
	public static void baseProcess(){
		//创建Properties对象
		Properties prop = new Properties();
		/**
		 * 由于继承了Hashtable，因此Properties也有put()方法、putAll()方法，但是不建议使用，因为可以放
		 * 任意类型的数据，而配置文件的内容就是String类型，建议使用setProperties(String,String)添加元素
		 */
		prop.setProperty("狗娃", "1");
		prop.setProperty("铁蛋", "2");
		
		//遍历所有元素
		Set<Map.Entry<Object, Object>> entrys = prop.entrySet();
		for(Entry<Object,Object> entry:entrys){
			System.out.println("键： "+entry.getKey()+" 值： "+entry.getValue());
		}
		
		//使用Properties生成配置文件,第一个参数是输出流对象，第二个参数是使用字符串描述这个配置文件的信息
		FileWriter fw = null;
		try {
			//输出默认是使用ISO-8859_1(拉丁码表)，中文会出现乱码
			//prop.store(new FileOutputStream("F:/person.properties"), "配置文件");
			
			//想要解决乱码问题，可以传给构造器一个输出字符流
			fw = new FileWriter("E:/person.properties");
			//store内部会判断传入的是否是缓冲流，不是缓冲流会生成一个BufferWriter()，最后会调用flush()方法。
			prop.store(fw, "配置文件");		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fw!=null)fw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 读取配置文件的信息
		 * 1. 创建Properties对象，注意要重新生成
		 */
		Properties _prop = new Properties();
		FileReader fr = null;
		try{
			//配置中出现中文的时候，还是使用字符流
			fr = new FileReader("E:/person.properties");
			//2. 将数据存储到Properties对象中
			_prop.load(fr);
			//3. 遍历（第二种遍历方式）
			//key的集合
			Set<String> set = _prop.stringPropertyNames();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String key = it.next();		//获得key
				System.out.println(_prop.getProperty(key));
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(fr!=null)fr.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
