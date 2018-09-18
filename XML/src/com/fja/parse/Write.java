package com.fja.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 * 对xml文档进行写入：
 *  
 */
public class Write {
	/**
	 * 写入的基本步骤： 
	 */
	@Test
	public void baseProcess(){
		//1.创建一个写入对象。构造器可以接受文件字节输出流和文件字符输出流
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream("E:/XMLWrite.xml"));
			//输出的内容，这里写出的是document对象
			Document document = new SAXReader().read(new File("./src/com/fja/parse/xml/students.xml"));
			//2.写出内容
			xmlWriter.write(document);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				//3.关闭输出流
				if(xmlWriter!=null)xmlWriter.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 修改xml的内容 
	 */
	@Test
	public void modify(){
		XMLWriter xmlWriter = null;
		try {
			//1.读入document对象
			Document document = new SAXReader().read(new File("./src/com/fja/parse/xml/students.xml"));
			
			//TODO 2.修改document对象
			
			
			//3.将修改后的document对象写出到xml文档中
			//3.1指定写出的格式
			OutputFormat compact = OutputFormat.createCompactFormat();		//紧凑格式,没有空格换行,节约空间
			OutputFormat pretty = OutputFormat.createPrettyPrint();			//漂亮格式,有空格换行,便于阅读
			
			//3.2指定生成的xml文档的编码,设置xml的编码和xml声明的encoding解码格式
			//可以保证xml编码和解码的一致性，避免中文乱码的出现
			compact.setEncoding("gbk");
			
			//3.3创建写出对象,XMLWriter的构造器可以接受两个参数，除了文件输出流之外，还可以接受一个格式对象（OutputFormat）。
			xmlWriter = new XMLWriter(new FileOutputStream("E:/XMLWrite.xml"),compact);
			
			//3.4写出
			xmlWriter.write(document);
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				//4.关闭输出流
				if(xmlWriter!=null)xmlWriter.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * 创建新的xml文档 
	 */
	@Test
	public void create(){
		XMLWriter xmlWriter = null;
		try {
			//1.创建一个document对象
			Document document = DocumentHelper.createDocument();
			
			//2.增加一个根标签节点,没有内容的时候是一个空标签
			Element rootElement = document.addElement("contactList");
			//3.添加子标签节点
			Element contactElement = rootElement.addElement("contact");
			Element nameElement = contactElement.addElement("name");
			
			//4.添加属性节点,一个参数是属性名，一个是属性值
			contactElement.addAttribute("id", "001");
			
			//5.添加文本节点
			nameElement.addText("铁蛋");
			
			OutputFormat pretty = OutputFormat.createPrettyPrint();			//漂亮格式,有空格换行,便于阅读
			
			pretty.setEncoding("utf-8");
			
			xmlWriter = new XMLWriter(new FileOutputStream("E:/contact.xml"),pretty);
			
			xmlWriter.write(document);
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(xmlWriter!=null)xmlWriter.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
