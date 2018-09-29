package com.fja.xml.sax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser {
	
	@Test
	public void storage(){
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler();
			parser.parse(new File("./src/com/fja/xml/sax/students.xml"), handler);
			List<Student> list = handler.getList();
			for(Student stu : list){
				System.out.println(stu);
			}
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
	List<Student> list = new ArrayList<Student>();
	
	private Student stu;
	
	private String cur;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		cur = qName;
		
		if("student".equalsIgnoreCase(qName)){
			stu = new Student();
		}
		
		if(attributes != null){
			for(int i = 0; i < attributes.getLength(); i++){
				String name = attributes.getQName(i);
				String value = attributes.getValue(i);
				if(name.equals("id")){
					stu.id = Integer.parseInt(value);
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("student".equalsIgnoreCase(qName)){
			list.add(stu);
			stu = null;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String text = new String(ch,start,length);
		if("age".equals(cur)){
			stu.age = Integer.parseInt(text);
			cur = null;
		}else if("name".equals(cur)) {
			stu.name = text;
			cur = null;
		}else if("hobby".equals(cur)){
			stu.hobby = text;
			cur = null;
		}
	}
	
	public List<Student> getList(){
		return list;
	}
}

class Student{
	int id;
	String name;
	int age;
	String hobby;
	
	@Override
	public String toString() {
		return "学号： "+id+"   姓名： "+name+"   年龄： "+age+"   兴趣： "+hobby;
	}
}