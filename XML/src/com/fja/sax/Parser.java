package com.fja.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析的原理：加载一点，读取一点，处理一点，占用内存资源较少
 * DOM解析的原理：一次性把xml文档加载进内存，在内存中构建Document树，占用内存多，容易造成内存溢出。
 * 
 * sun公司提供的SAXParser，内置在jdk中，org.xml.sax包中
 */
public class Parser {
	
	@Test
	public void baseProcess(){
		/**
		 * 最主要的方法parse(File f,DefaultHandler dh)
		 * 参数File f 要读取的xml文件的对象
		 * 参数DefaultHandler dh SAX的事件处理程序
		 */
		
		//1.获得SAXParser的工厂，构造方法是protected，因此不能直接new
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//2.获得SAXParser对象，SAXParser类的构造方法也是protected的，因此不能直接创建实例
		try {
			SAXParser parser = factory.newSAXParser();
		//3.调用parse方法
			File file = new File("E:/XMLWrite.xml");
			MyHandler handler = new MyHandler();
			parser.parse(file, handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

/**
 * DefaultHandler的常用方法：
 * 	 1. startDocument()
 * 		开始读取文件时调用的方法
 * 	 2. endDocument()
 * 		读取文件结束时调用的方法
 * 	 3. startElement(String uri, String localName, String qName, Attributes attributes)
 * 		开始读取某一个标签节点时调用，传入的参数有当前读取的标签节点的标签名(qName)，还有标签中含有的属性节点的集合(attributes)
 * 	 4. endElement(String uri, String localName, String qName)
 * 		结束读取某一个标签节点时调用
 * 	 5. characters(char[] ch, int start, int length)
 * 		读取本文节点时调用，会将目前读到的所有文本节点放到char[]中，start则是当前读的文本在char[]数组中的开始索引，length则是文本的字节长度，
 * 		三个参数可以通过new String(ch,start,length)获得把文本节点的内容字符串
 */
class MyHandler extends DefaultHandler{
	@Override
	public void startDocument() throws SAXException {
		System.out.println("===开始读取xml===");
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("===结束读取xml===");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("开始标签==> "+qName);
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("结束标签==> "+qName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("读取的文本节点==> "+new String(ch,start,length));
	}
	
}
