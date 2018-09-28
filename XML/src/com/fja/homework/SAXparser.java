package com.fja.homework;
//将xml中的数据封装到对象中

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class SAXparser {

	public void storage(){
		List<Student> list = new ArrayList<Student>();
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			
			parser.parse(new File("E:/XMLWrite.xml"),new XMLHandler(list));
			
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
	
	private List<Student> list;	
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		
	}

	public XMLHandler(List<Student> list) {
		super();
		this.list = list;
	}

	public List<Student> getList() {
		return list;
	}
	
}


class Student{
	
	private int id;
	
	private String name;
	
	private int age;
	
	private String gender;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
