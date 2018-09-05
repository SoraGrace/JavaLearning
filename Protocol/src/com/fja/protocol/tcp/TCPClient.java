package com.fja.protocol.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP通讯协议特点：
 * 1. 是基于IO流进行数据的传输，是面向链接的。(UDP是通过数据包进行传输)
 * 2. TCP进行数据传输的时候是没有大小限制的。
 * 3. TCP是面向链接，通过三次握手的机制保证数据的完整性。 
 * 4. TCP是面向链接的，速度慢。(UDP面向无连接，速度快)
 * 5. TCP是区分客户端与服务端的。(UDP不区分)
 * 
 * 比如：打电话，下载，文件传输
 * 
 * TCP协议的相关类：
 * 		Socket(客户端)：一旦启动马上要和服务端建立链接
 * 		ServerSocket(服务端类)
 */
public class TCPClient {
	public static void main(String[] args) {
		//1.建立TCP的服务,因为要建立链接，所以要将IP对象和端口传入构造器
		Socket socket = null;
		try {
			socket = new Socket(InetAddress.getLocalHost(),9090);
			//2.获取到对应的输出流对象
			OutputStream os = socket.getOutputStream();
			//3.利用字节输出流将数据输出
			os.write("Hello Server".getBytes());
			//读取服务端返回的数据
			InputStream is = socket.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			len = is.read(buf);
			System.out.println(new String(buf,0,len));
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			//4.关闭Socket，关闭Socket之后字节输出流也会关闭。
			try {
				if(socket!=null)socket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
