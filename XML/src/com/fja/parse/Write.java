package com.fja.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Attribute;
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
	
	
	/**
	 * 修改xml属性值和文本 
	 */
	@Test
	public void _modify(){
		XMLWriter xmlWriter = null;
		
		try {
			Document document = new SAXReader().read(new File("E:/contact.xml"));
			
			/**
			 * 修改属性节点
			 * 方法一：setValue()
			 */
			//1.1找到ID的属性节点
			Element root = document.getRootElement();
			Attribute id = root.element("contact").attribute("id");
			
			//1.2修改
			id.setValue("003");
			
			/**
			 * 修改属性节点
			 * 方法二：setValue()
			 */
			//2.1找到属性节点的父节点
			Element parent = root.element("contact");
			//2.2添加同名的属性，因为相同的属性只能有一个，因此会将原有的属性值覆盖
			parent.addAttribute("id", "005");
			
			/**
			 * 修改文本节点： 
			 */
			//1.得到标签对象
			Element elem = root.element("contact").element("name");
			//2.修改文本
			elem.setText("李四");
			
			OutputFormat pretty = OutputFormat.createPrettyPrint();			//漂亮格式,有空格换行,便于阅读
			
			pretty.setEncoding("utf-8");
			
			xmlWriter = new XMLWriter(new FileOutputStream("E:/contact.xml"),pretty);
			
			xmlWriter.write(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 删除标签和属性节点 
	 */
	@Test
	public void delete() {
		SAXReader reader = new SAXReader();
		XMLWriter writer = null;
		try {
			File file = new File("E:/contact.xml"); 
			
			Document doc = reader.read(file);
			
			Element rootElement = doc.getRootElement();
			
			Element contactElem = rootElement.addElement("contact");
			
			contactElem.addAttribute("id", "002");
			
			Element nameElem = contactElem.addElement("name");
			
			nameElem.addText("狗剩");
			
			writer = new XMLWriter(new FileOutputStream(file));
			
			writer.write(doc);
			
			//删除上面新增的name标签
			/**
			 * 方法一：detach()
			 * 1.1 获取标签对象 
			 */
			doc = reader.read(file);
			
			rootElement = doc.getRootElement();
			
			Element second = rootElement.elements("contact").get(1);
			
			Element name = second.element("name");
			
			//1.2删除标签对象
			name.detach();
			
			/**
			 * 方法二：remove() 
			 */
			//2.1获得标签对象
			Element first = rootElement.elements("contact").get(0);
			Element _name = first.element("name");
			
			//2.2获得想要删除的标签的父节点
			Element parent = _name.getParent();
			
			//通过父标签删除目标(可以是Node、Element、Attribute、Text等等)
			parent.remove(_name);
			
			/**
			 * 删除属性节点和删除标签节点的步骤是一样的 
			 * 3.1获得属性节点的对象
			 */
			Attribute idAttr = second.attribute("id");
			idAttr.detach();
			
			writer = new XMLWriter(new FileOutputStream(file));
			
			writer.write(doc);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(writer!=null)writer.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
