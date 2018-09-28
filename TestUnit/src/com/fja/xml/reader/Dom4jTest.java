package com.fja.xml.reader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Dom4jTest {
	
	public static void main(String[] args) {
		reader();
	}
	
	public static void reader(){
		SAXReader reader = new SAXReader();
		try {
			Document dom = reader.read(new File("./src/com/fja/xml/source/students.xml"));
			//获得根节点
			Element root = dom.getRootElement();
			System.out.println(root.getName());
			
			//获取所有同名的节点
			List<Element> list = root.elements("student");
			for(Element elem:list){
				System.out.println("student标签的id属性： "+elem.attributeValue("id"));
			}
			
			//获取指定标签名称的节点
			Element stu = root.element("student");
			Element name = stu.element("name");
			
			//获取标签中的内容
			System.out.println(name.getText());
			//获取标签的属性
			System.out.println(stu.attributeValue("id"));
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
