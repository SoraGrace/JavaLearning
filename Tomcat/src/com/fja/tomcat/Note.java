package com.fja.tomcat;
/**
 * 启动tomcat
 * 	1.找到%tomcat%/bin/startup.bat 
 * 	2.运行
 * 
 * 关闭tomcat
 * 	1.找到%tomcat%/bin/shutdown.bat 
 * 	2.运行
 * 
 * 常见问题：
 * 	闪退：没有在系统环境变量中找到JAVA_HOME变量，因此无法找到jvm，tomcat无法启动
 * 	
 * 	端口占用：修改tomcat的默认端口。
 * 		步骤一：找到%tomcat%/conf/server.xml
 * 		步骤二：修改
 * 			<Connector port="8080" 
 * 				protocol="HTTP/1.1" connectionTimeout="20000" 
 * 				redirectPort="8443" />
 * 			标签中的port的端口号
 * 	
 * 	catalina环境变量问题：
 * 		原因： tomcat启动时除了查找JAVA_HOME变量之外，还会找CATALINA_HOME变量，
 * 			  这个变量的作用是设置tomcat的根目录。也就是说，当电脑里存在两个tomcat的时候，
 * 			  运行任意的startup.bat都会运行CATALINA_HOME变量指定的那个tomcat软件。
 * 			  没有设置CATALINA_HOME变量，则会运行startup.bat所在目录下的tomcat。
 * 	
 * 	tomcat的作用：
 * 		将文件共享给外部
 * 		方法：将需要共享的文件放到%tomcat%/webapps/指定的文件夹/目录下
 * 			 http://localhost:8080/就指向了webapps目录
 * 
 * 	在浏览器输入网址之后：浏览器会去本地找到hosts(域名映射文件),找到域名对应的ip地址，比如localhost对应的就是127.0.0.1。
 * 					 如果在本地没找到，则会去网络运营商的dns服务器寻找。
 * 					 找到服务器之后，通过端口找到监听这个端口的软件，进入默认对外共享文件的文件夹(tomcat中就是webapps),
 * 					 通过最后的资源名找到对应的资源。
 * 
 */
public class Note {

}
