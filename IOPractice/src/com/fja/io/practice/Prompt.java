package com.fja.io.practice;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 需求：
 * 试用Properties实现软件运行三次后提示购买正版，推出jvm 
 */
public class Prompt {

	public static void main(String[] args) {
		Software soft = new Software();
		soft.run();
	}
}


class Software{
	
	public void run(){
		File file = new File("src/com/fja/io/practice/count.properties");
		Properties prop = new Properties();
		int count = 0;		//默认使用0次
		//判断文件是否存在，存在中文件中读取数据
		if(file.exists()){
			try {
				FileReader fr = new FileReader(file);
				prop.load(fr);
				//获取使用次数
				count = Integer.parseInt((String)prop.get("count"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		//修改使用次数
		prop.setProperty("count", String.valueOf(++count));
		//写出
		try {
			FileWriter fw = new FileWriter(file);
			prop.store(fw,"记录使用次数");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(count>=3){
			System.out.println("请购买正版软件");
			System.exit(0);
		}
	}
}