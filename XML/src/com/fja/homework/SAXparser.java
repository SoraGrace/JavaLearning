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

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class SAXparser {
	
	@Test
	public void storage(){
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			
			XMLHandler xmlHandler = new XMLHandler();
			
			parser.parse(new File("E:/XMLWrite.xml"),xmlHandler);
			
			List<Student> list = xmlHandler.getList();
			
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
	
	private List<Student> list = new ArrayList<Student>();	
	
	private Student stu;
	
	private String currentTagNode;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		//保存当前正在读取的标签节点的名称
		currentTagNode = qName;
		
		//当qName是student时开始封装数据
		if("student".equals(qName)) {
			
			stu = new Student();
			
			//如果Attributes不为空，说明有id属性
			if(attributes != null) {
				//通过索引拿到id的值
				for(int i = 0; i < attributes.getLength(); i++){
					String name = attributes.getQName(i);
					String value = attributes.getValue(i);
					//是id属性则进行保存
					if("id".equals(name)){
						stu.setId(Integer.parseInt(value));
					}
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//读完一个标签将当前读取的标签变量置空
		currentTagNode = null;
		
		//某一个学生的数据已经读取完毕，将学生对象放入集合中
		if("student".equals(qName)) {
			list.add(stu);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		//当前读取的文本节点内容
		String text = new String(ch,start,length);
		//判断当前读取的标签节点，选择正确的成员变量进行封装
		if(currentTagNode != null){
			//currentTagNode为空为报空指针异常
			switch(currentTagNode){
				case "name":
					stu.setName(text);
					break;
				case "age":
					stu.setAge(Integer.parseInt(text));
					break;
				case "gender":
					stu.setGender(text);
					break;
			}
		}
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
	
	
	@Override
	public String toString() {
		return "学号： "+id+"  姓名："+name+"   年龄： "+age+"   性别： "+gender;
	}
}
