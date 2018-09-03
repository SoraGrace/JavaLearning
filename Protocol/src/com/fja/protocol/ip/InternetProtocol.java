package com.fja.protocol.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 网络编程：
 * 主要用于解决计算机与计算机之间的数据传输问题 
 * 
 * 网络编程：不需要基于HTML页面，就可以完成数据之间的传输。
 * 网页编程：需要基于HTML页面进行数据的交互，比如：oa(办公自动化)。
 * 
 * 计算机网络：
 * 分布在不同地域的计算机，通过外部的设备（路由器、交换机）链接起来达到了消息互通、资源共享的效果时就可以称作一个计算机网络
 * 
 * 网络通信的三要素：
 * IP：由32位的二进制数据组成的，为了方便记忆把IP地址分成了4份，每份8bit，取值范围是0-255。
 * 端口：用于标明该消息是给哪个应用程序处理的。只不过是一个标识符而已。
 * 协议：
 * 
 * IP地址 = 网络号 + 主机号
 * 
 * 子网掩码：作用是表明IP地址中哪些是网络号，哪些是主机号。255表示网络号，255.255.255.0表示前三位是网络号，最后一位是主机号
 * 
 * IP地址的分类：
 * 	1.A类IP地址：一个网络号+三个主机号。可以配2^24台计算机,政府单位使用
 * 	2.B类IP地址：两个网络号+两个主机号。可以配2^16台计算机,事业单位使用
 * 	3.C类IP地址：三个网络号+一个主机号。可以配2^8台计算机,私人使用（实际使用其实是，一个IP地址通过路由器或者交换机进行分发的）。
 * 
 * 
 * JAVA中网络编程的所有类都在java.net下
 * JAVA中的IP类：
 * InetAddress可以用来描述IP地址
 */
public class InternetProtocol {

	public static void main(String[] args) {
		baseProcess();
	}
	
	public static void baseProcess(){
		//getLocalHost()返回本机IP对象，【注意】是对象
		try {
			InetAddress address = InetAddress.getLocalHost();
			//getHostAddress()方法，返回IP地址的字符串
			String ip = address.getHostAddress();
			System.out.println(ip);
			
			//getHostName()方法，返回主机名
			String name = address.getHostName();
			System.out.println(name);
			
			//getByName()通过主机名或者IP字符串获取他人机器的IP地址的对象
			InetAddress _address = InetAddress.getByName("192.168.1.104");
			//DESKTOP-J6S55SA
			System.out.println(_address.getHostName());
			
		} catch (UnknownHostException e) {
			//没有装网卡的时候，本机是不会有IP地址的,因此getLocalHost()会抛出一个异常
			e.printStackTrace();
		}
	}
}
