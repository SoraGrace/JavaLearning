package com.fja.xml.xpath;

import java.io.File;

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
			//通过id获取到第三位学生狗蛋的标签节点
			Node node = document.selectSingleNode("/student[@id='003']");
			Element thirdStu = null;
			//类型转换
			if(node instanceof Element){
				thirdStu = (Element)node;
			}
			//打印学生名字
			if(thirdStu != null){
				System.out.println(thirdStu.elementText("name"));
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
