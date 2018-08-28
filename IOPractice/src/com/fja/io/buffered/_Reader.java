package com.fja.io.buffered;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 输入字符流：
 * ------| Reader 所有出入字符流的基类
 * ----------| InputStreamReader  转化流      字节流+字典码
 * -------------------| FileReader 文件输入字符流
 * ----------|BufferedReader 缓冲输入字符流   提高了读取的效率，拓展了FileReader的功能
 */
public class _Reader {
	
	public static void main(String[] args) {
		baseProcess();
		//测试实现的readLine()方法
		File _file = new File("src\\com\\fja\\io\\buffered\\_Writer.java");
		FileReader fr = null;
		try {
			fr = new FileReader(_file);
			String line = null;
			while((line = _readLine(fr))!=null){
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fr!=null)fr.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void baseProcess(){
		//缓冲流都不具备读写的能力
		File file = new File("E:\\test.txt");
		File _file = new File("src\\com\\fja\\io\\buffered\\_Writer.java");
		BufferedReader br = null;
		try{
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			int content = br.read();		//读取了一个字符
			System.out.println((char)content);
			//拓展功能，读取一行
			String line = br.readLine();
			System.out.println(line); //一次可以读取一行代码
			
			//用循环读取整个文件的内容
			fr = new FileReader(_file);
			br = new BufferedReader(fr);
			//注意：这里判断的条件不是-1了
			while((line = br.readLine())!=null){
				System.out.println(line);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(br!=null)br.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	//实现readLine()方法
	public static String _readLine(Reader fileReader){
		StringBuffer sb = new StringBuffer();
		int content = 0;
		try {
			while((content = fileReader.read())!=-1){
				if((char)content=='\r'){
					continue;
				}else if((char)content=='\n'){
					break;				//是\n中断循环返回StringBuffer
				}else{	//不是\r或者\n就添加到StringBuffer中去
					sb.append((char)content);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(content==-1){
			return null;
		}else{
			return sb.toString();
		}
	}
}
