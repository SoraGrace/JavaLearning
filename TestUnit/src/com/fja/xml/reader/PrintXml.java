package com.fja.xml.reader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 *  需求：在控制台打印整个xml文档
 */
public class PrintXml {
	@Test
	public void print(){
		SAXReader saxReader = new SAXReader();
		
		try {
			Document dom = saxReader.read(new File("./src/com/fja/xml/source/students.xml"));
			Element rootElement = dom.getRootElement();
			
			String str = childNodes(rootElement);
			
			System.out.println(str);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public String childNodes(Element elem){
		Iterator<Node> nodeIterator = elem.nodeIterator();
		//标签节点的缓冲字符序列
		StringBuffer elemBuffer = new StringBuffer();
		//属性节点序列
		StringBuffer attrBuffer = new StringBuffer();
		//标签名
		String elemName = elem.getName();
		
		//遍历属性节点
		List<Attribute> attributes = elem.attributes();
		for(Attribute attr : attributes){
			attrBuffer.append(" "+attr.getName()+"=\""+attr.getValue()+"\"");
		}
		String attrStr = attrBuffer.toString();
		
		elemBuffer.append("<"+elemName+attrStr+">");
		
		while(nodeIterator.hasNext()){
			Node node = nodeIterator.next();
			
			if(node instanceof Text) {
				Text text = (Text)node;
				elemBuffer.append(text.getText());
			}else if(node instanceof Element) {
				String childNodeStr = childNodes((Element)node);
				elemBuffer.append(childNodeStr);
			}
		}
		elemBuffer.append("</"+elemName+">");
		
		return elemBuffer.toString();
	}
}
