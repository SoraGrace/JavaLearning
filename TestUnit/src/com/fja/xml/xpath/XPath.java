package com.fja.xml.xpath;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

//简单实用xpath
public class XPath {
	
	//基本实用
	@Test
	public void baseProcess(){
		SAXReader saxReader = new SAXReader();
		
		try {
			Document document = saxReader.read(new File("D:/PracticeSpace/students.xml"));
			
			//通过id获取到第三位学生狗剩的标签节点
			Node node = document.selectSingleNode("/Students/student[@id='003']");
			Element thirdStu = null;
			//类型转换
			if(node instanceof Element){
				thirdStu = (Element)node;
			}
			//打印学生名字
			if(thirdStu != null){
				System.out.println(thirdStu.elementText("name"));		//狗剩
			}
			
			//获取所有的id属性，并打印
			List<Node> nodes = document.selectNodes("//@id");			// "//"表示在任意节点的,类似于相对路径
			for(Node n : nodes){
				Attribute attr = null;
				if(n instanceof Attribute){
					attr = (Attribute)n;
					System.out.println(attr.getValue());
				}
			}
			
			//获取所有学生的爱好
			List<Node> hobbys = document.selectNodes("//hobby");
			for(Node n : hobbys){
				Element hobby = null;
				if(n instanceof Element){
					hobby = (Element)n;
					System.out.println(hobby.getText());
				}
			}
			
			//last()函数，获得最后一个节点
			//获取Students标签下的最后一个标签节点
			Node lastNode = document.selectSingleNode("//Students/*[last()]");
			if(lastNode instanceof Element){
				Element lastElem = (Element)lastNode;
				Attribute attr = lastElem.attribute("id");
				System.out.println(attr.getValue());			//003
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
