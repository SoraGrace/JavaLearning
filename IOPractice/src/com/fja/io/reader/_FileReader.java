package com.fja.io.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.management.RuntimeErrorException;

/**
 * 为什么要使用字符流？
 * 因为输入字节流读取的是文件中的二进制数据，读到的数据并不会帮你转换成看得懂的字符 
 * 
 * --------|Reader
 * ------------|FileReader 读取文件的输入字符流
 * 
 */
public class _FileReader {

	public static void main(String[] args) {
		outputStreamTest();
		inputStreamTest();
		baseProcess();
	}
	
	//文件输入字符流的基本使用
	static void baseProcess(){
		System.out.print("\n");
		//步骤一： 找到文件
		File file = new File("E:\\WriteTest.txt");
		//步骤二：建立数据通道
		FileReader fileReader = null;
		try{
			fileReader = new FileReader(file);
			//步骤三：读取数据，读取【单个字符】，注意是单个字符
			int content = 0;
			while((content = fileReader.read())!=-1){
				System.out.print((char)content);
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}finally{
			//步骤四：关闭流
			try {
				if(fileReader!=null)fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 优势：
		 * 当中文中有英文字母时，可以正常读取，不会再出现乱码了 
		 */
	}
	
	
	
	
	/* 字节流的不足 */
	//使用字节流读取文件中的中文
	static void inputStreamTest(){
		File file = new File("E:\\WriteTest.txt");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int content = 0;
			while((content = fis.read())!=-1){
				System.out.print((char)content);	//这里输出的是乱码，因为在UTF-8中中文占三个字节，而read方法只能读取一个字节的数据
			}
			//使用byte[]数组再读取一次
			byte[] buf = new byte[3];				//一次读取三个字节进行编码
			fis = new FileInputStream(file);
			int len = 0;
			System.out.print("\n");
			while((len = fis.read(buf))!=-1){
				System.out.print(new String(buf,0,len));
			}
			/**
			 * 当使用byte[]之后在控制台成功了文件中的中文内容，但是注意 
			 * FileInputStream是没有解码能力的，之所以能把字节转化成能看懂的文字，也是因为使用了new String()的解码能力
			 * 这样使用还是有缺陷，当汉字和英文混在一起的时候，还是会出现乱码。
			 */
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fis!=null)fis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	//使用字节流输出中文到文件中
	static void outputStreamTest(){
		File file = new File("E:\\WriteTest.txt");
		String str = "老当益壮宁移白首之心";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(str.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		/**
		 * 写出中文成功，这里一定要注意，FileOutputStream是没有编码能力的，
		 * 这里将中文转换成为二进制数据是依靠了getBytes()方法的编码能力
		 */
	}
}

