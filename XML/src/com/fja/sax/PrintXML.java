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

//通过SAXParser将xml文档打印到控制台
public class PrintXML {
	
	@Test
	public void print(){
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler();
			//将xml保存到了handler的Stringbuffer中
			parser.parse(new File("E:/XMLWrite.xml"),handler);
			//将Stringbuffer中的内容打印出来
			System.out.println(handler.getContent());
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


class XMLHandler extends DefaultHandler{
	
	private StringBuffer buffer = new StringBuffer();
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//拼接开始标签
		buffer.append("<"+qName);
		//循环拼接属性
		if(attributes != null){
			//getLength()获取Attributes集合的长度
			for(int i = 0; i < attributes.getLength(); i++){
				//通过索引获得属性节点的名称
				String name = attributes.getQName(i);
				//通过索引获得属性节点的值
				String value = attributes.getValue(i);
				buffer.append(" "+name+"=\""+value+"\"");
			}
		}
		//循环完了属性就可以加上右括号了">"，开始标签拼接完毕
		buffer.append(">");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//拼接结束标签，没有属性节点
		buffer.append("</"+qName+">");
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buffer.append(new String(ch,start,length));
	}

	public String getContent() {
		return buffer.toString();
	}
	
	
}