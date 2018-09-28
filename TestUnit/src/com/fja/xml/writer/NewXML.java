package com.fja.xml.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

//新增一个xml文档
public class NewXML {
	
	@Test
	public void create(){
		XMLWriter writer = null;
		try {
			//创建document
			Document document = DocumentHelper.createDocument();
			//创建根节点
			Element rootElement = document.addElement("Students");
			
			//添加学生标签
			Element stu_first = rootElement.addElement("student");
			//添加学号
			stu_first.addAttribute("id", "001");
			//添加名字
			Element name_first = stu_first.addElement("name");
			name_first.addText("铁蛋");
			//添加年龄
			Element age_first = stu_first.addElement("age");
			age_first.addText("10");
			//添加兴趣
			Element hobby_first = stu_first.addElement("hobby");
			hobby_first.addText("睡觉");
			
			//添加学生标签
			Element stu_second = rootElement.addElement("student");
			//添加学号
			stu_second.addAttribute("id", "002");
			//添加名字
			Element name_second = stu_second.addElement("name");
			name_second.addText("美美");
			//添加年龄
			Element age_second = stu_second.addElement("age");
			age_second.addText("11");
			//添加兴趣
			Element hobby_second = stu_second.addElement("hobby");
			hobby_second.addText("跳舞");
			
			
			//添加学生标签
			Element stu_third = rootElement.addElement("student");
			//添加学号
			stu_third.addAttribute("id", "003");
			//添加名字
			Element name_third = stu_third.addElement("name");
			name_third.addText("狗剩");
			//添加年龄
			Element age_third = stu_third.addElement("age");
			age_third.addText("9");
			//添加兴趣
			Element hobby_third = stu_third.addElement("hobby");
			hobby_third.addText("下棋");
			
			//创建文件输出字节流
			FileOutputStream fos = new FileOutputStream(new File("D:/PracticeSpace/students.xml"));
			writer = new XMLWriter(fos);
			//创建xml文件格式
			OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
			//设置编码格式
			prettyFormat.setEncoding("utf-8");
			//写出
			writer.write(document);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
