package com.fja.protocol.http;
/**
 * 什么是http协议：
 * 	  http协议：对浏览器客户端与服务器之间数据传输的格式规范 
 * 
 * 
 * 
 * Http请求的组成：
 * 		一个Http请求由一个请求行+一个请求头(多个key/value组成)+(可能有一个实体，post请求的时候有，get请求时没有)
 * 
 * 
 * 
 * 问：一个web页面中，使用img标签引用了三幅不同的图片，当浏览器访问这个web页面的时候，浏览器会访问几次服务器,即发送了几次HTTP请求。
 * 答：四次，访问页面一次，三张不同的图片各一次。
 * 
 * 问：一个web页面中，使用img标签引用了三幅相同的图片，当浏览器访问这个web页面的时候，浏览器会访问几次服务器,即发送了几次HTTP请求。
 * 答：两次。其实HTTP请求还是发了四次，但是，浏览器会做优化，将相同的请求拦截，直接把资源返回，因此向服务器(注意：是向服务器)总共发了两次请求。
 * 
 * 
 * 
 * 请求行的格式：
 * 	请求方式(GET/POST)+统一资源标记符(URI)+协议版本(1.0/1.1)
 * 	例子： GET /main/index.html HTTP/1.1
 * 
 * 
 * 
 * URL/URI：
 * 	URL：统一资源定位符。http://localhost:8080/main/index.html。只能用于定位互联网资源，是URI的子集
 *  URI：统一资源标记符。/main/index.html。可用于标记任何资源(适用于任何协议)，可以是本地文件系统(file:///main/index.html)，
 *      或者是局域网的资源，当然也可以是互联网资源(这时候的URI就是URL，走http协议)
 * 
 * 
 * 
 * Http协议版本：
 * 		http1.0: 当前客户端(浏览器)与服务器端建立连接之后，只能发送一次请求，一次请求之后关闭连接。
 * 		http1.1: 当前客户端(浏览器)与服务器端建立连接之后，可以在一次连接中发送多次请求。
 * 
 * 
 * 
 * GET/POST：
 *  	GET：    1). 参数跟在url后面，多个参数以"&"分隔。
 *  	  	  2). 提交参数数据有限制，不超过1KB
 *  	      3). 不能用于提交敏感数据
 *  		  4). 浏览器的默认提交方式
 *  
 * 		POST： 1). 参数不会跟着URL后面，而是跟在请求的实体内容（请求体）中。
 * 		               参数不以"?"开头，多个参数之间以"&"分隔。
 * 			  2). 提交的参数没有限制。
 * 		  	  3). 可用于提交敏感数据，比如密码
 * 
 * 
 * 请求头： 是由多个key/value组成
 * 常见的key:value：
 * 	Accept:text/html,image/*       		--->浏览器接受的数据类型
 * 	Accept-Charset:ISO-8859-1      		--->浏览器接受的编码格式
 * 	Accept-Encoding:gzip,compress  		--->浏览器接受的数据压缩格式
 * 
 *  Host:cm.bilibili.com		   		--->当前请求访问的目标地址(必须要有)
 *  Referer:https://www.bilibili.com/	--->当前请求来自于哪里
 *  
 *  if-Modified-Since:Tue,11 Jul 2000   --->浏览器最后的缓存时间
 *  Connection:Keep-Alive               --->浏览器与服务器的连接状态，Keep-Alive表示保持连接;close表示关闭连接
 * 											【注意】：当在请求头中带上Connection:close时，哪怕使用http1.1的协议，也会在请求一次后断开连接。
 * 
 * 
 *  HttpServletRequest中可以获得请求头信息
 *  	web服务器程序(tomcat、jboss)会将这些信息封装到HttpServletRequest对象中
 *  	
 *  	请求行：
 *  	request.getMethod()         获取请求行中的请求方式
 *  	request.getRquestURI()		获取请求行中的统一资源标记符
 *  	request.getProtocol()		获取请求行中的协议
 *  	
 *  	请求头：
 *  	request.getHeader(头名称)    根据key返回value
 *  	request.getHeaderNames()    获得所有的key，返回值是Enumeration<String>的集合
 *  	
 *  	实体内容：
 *  	request.getInputStream()    获取输入字节流，循环read()可获得实体内容
 *  
 *  
 *  
 *  响应行的格式：
 *  	协议版本+状态码+状态描述
 *  
 *  常见状态码:
 *  	200       请求处理完成并完美返回
 *  	302       请求需要进一步细化
 *  	404       客户端错误，访问的资源找不到(用户输入错误uri)
 *      500   服务器内部发生错误
 *  
 *  状态描述：
 *  	对状态码的补充说明
 *  	Ok
 *  	Not Found
 *  
 *  常见的响应头：
 *  	Location：www.xxxx.com             				--->表示重定向的地址，该头和302的状态码一起使用
 *  	Server：appache tomcat			  				--->表示服务器的类型
 *  	Content-Type：text/html;charset=utf-8			--->表示服务器发送给浏览器的数据类型和内容编码【重要】
 *  	Content-Dispositon：attachment;filename=xxx.zip 	--->告诉浏览器以下载的方式打开资源
 *  	Set-Cookie：SS=Q0=5LB;path=/search				--->发送给浏览器的cookie信息
 */
public class Note {

}
