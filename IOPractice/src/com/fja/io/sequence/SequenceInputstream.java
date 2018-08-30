package com.fja.io.sequence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Vector;
/**
 * 序列流 
 */
public class SequenceInputstream {

	public static void main(String[] args) {
		//baseProcess();
		merge();
	}
	
	//合并两个文件进行输出
	public static void baseProcess(){
		File inFile = new File("E:/sequence/1st.txt");
		File _inFile = new File("E:/sequence/2nd.txt");
		File outFile = new File("E:/sequence/3rd.txt");
		SequenceInputStream sis = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(outFile);
			FileInputStream fis = new FileInputStream(inFile);
			FileInputStream _fis = new FileInputStream(_inFile);
			//建立序列流
			sis = new SequenceInputStream(fis,_fis);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = sis.read(buf))!=-1){
				fos.write(buf,0,len);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				/**
				 * 只需要关闭序列流就行了，不需要把所有的输入字节流都关闭
				 * 序列流的close方法会调用while循环，如果有下一个流则取出并关闭
				 */
				if(sis!=null)sis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}finally{
				try {
					if(fos!=null)fos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	
	//合并两个以上的文件，进行输出,使用传入枚举的构造器
	public static void merge(){
		//把E盘下的1st.txt    2nd.txt   3rd.txt文件的内容进行合并，输出到4th.txt
		File inFile = new File("E:/sequence/1st.txt");
		File _inFile = new File("E:/sequence/2nd.txt");
		File $inFile = new File("E:/sequence/3rd.txt");
		File outFile = new File("E:/sequence/4th.txt");
		
		SequenceInputStream sis = null;
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(outFile);
			FileInputStream fis = new FileInputStream(inFile);
			FileInputStream _fis = new FileInputStream(_inFile);
			FileInputStream $fis = new FileInputStream($inFile);
			
			//先将输入流存入Vector
			Vector<FileInputStream> v = new Vector<FileInputStream>();
			v.add(fis);
			v.add(_fis);
			v.add($fis);
			//Vector通过elements()方法进行迭代，该方法返回的就是一个枚举类（Enumeration）
			sis = new SequenceInputStream(v.elements());
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = sis.read(buf))!=-1){
				fos.write(buf,0,len);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(sis!=null)sis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}finally{
				try {
					//先开后关的原则
					if(fos!=null)fos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
