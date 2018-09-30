package com.fja.web.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormSubmitServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取所有提交参数的方法     getQueryString()
		String paramStr = req.getQueryString();
		System.out.println(paramStr);
		
		/**
		 * 与doPost方法一样可以通过getParameter()方法快速获取指定的提交数据
		 * 但是doGet方法不能用setCharacterEncoding()方法解决中文乱码问题，因为，get提交方式的数据不是在实体内容中的。
		 * 因此只能通过手动编码再解码的过程解决乱码问题。
		 * 造成乱码的过程：
		 * utf-8编码的数据-->tomcat使用iso-8859-1进行解码-->中文乱码
		 * 解决方法：
		 * 中文乱码-->手动用iso-8859-1进行编码-->utf-8编码的数据-->手动使用utf-8进行解码-->正常数据
		 * 
		 * ps.
		 * 	iso-8859-1是唯一的，解码中出现乱码不会丢失数据的码表。也就是说出现解码乱码，再用iso-8859-1编码后，还能还原到原有的数据。
		 * 	原因是iso-8859-1编码范围使用了单字节内的所有空间，也就是不存在一个字符对应多个字节的情况，比如在其他码表中会出现多个字节没有使用，
		 * 	则这些字节就对应了一个无效的字符，这就造成了一个字符对应多个字节。
		 */
		//乱码
		String username = req.getParameter("username");		
		
		//手动用iso-8859-1进行编码
		byte[] arr = username.getBytes("iso-8859-1");
		
		//手动使用utf-8进行解码
		username = new String(arr,"utf-8");
		
		System.out.println("username: "+username);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取在实体内容中的参数   getInputStream()
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
		int content = 0;
		StringBuffer sb = new StringBuffer();
		while((content = bufferedReader.read()) != -1){
			sb.append((char)content);
		}
		String paramStr = sb.toString();
		/**
		 * URLDecoder 和 URLEncoder 用于完成普通字符串 和 application/x-www-form-urlencoded MIME 字符串之间的相互转换
		 * 浏览器首先会将这些中文字符进行编码然后再发送给服务器。实际上，浏览器会将它们转换为 application/x-www-form-urlencoded MIME 字符串
		 * 
		 * 整个过程：浏览器-->MIME字符转换-->根据页面的content-type指定的编码表进行编码-->发送给服务器
		 */
		paramStr = URLDecoder.decode(paramStr, "utf-8");
		
		//分割字符串，截取参数
		String[] arr = paramStr.split("&");
		//遍历
		for(String str : arr) {
			String key = str.split("=")[0];
			String value = str.split("=")[1];
			System.out.println("key: "+key+"      value: "+value);
		}
		
		/**
		 * 上面的方法比较繁琐，tomcat有封装好的方法
		 * getParameter()			        根据参数的key获得对应的value
		 * req.getParameterNames()		获得所有参数的key
		 * 
		 * 还需要注意的是中文解码的问题，上述的方法(tomcat)用的是iso-8859-1,而页面编码用的是utf-8。
		 * setCharactorEncoding()可以设置实体内容的解码方式，从而解决getParameter()方法的中文乱码问题。
		 * 【注意】这个方法只能设置实体内容的解码方式，因此只能解决post方法提交的数据中文乱码问题，get方式并不适用
		 */
		
		//在使用getParameter方法前，先调用setCharactorEncoding()
		req.setCharacterEncoding("utf-8");
		
		String gender = req.getParameter("gender");
		System.out.println(gender);
		
		//遍历所有提交的数据
		//获取所有的key
		Enumeration<String> keys = req.getParameterNames();
		
		while(keys.hasMoreElements()){
			//获得所有的key，在通过getParameter方法获取对应的值
			String key = keys.nextElement();
			String value = req.getParameter(key);
			System.out.println(value);
		}
		
		/**
		 * 【注意】
		 *  getParameter()方法有一个缺陷，当提交的参数是一个key对应多个value的时候，只能获取其中的一个value。
		 *  比如上面的代码不能将所有hobby对应的值全部打印出来。当一个key对应多个value的时候，就需要用到
		 */
		String[] values = req.getParameterValues("hobby");
		//打印所有的hobby
		for(String value : values){
			System.out.println("hobby: "+value);
		}
	}
}
