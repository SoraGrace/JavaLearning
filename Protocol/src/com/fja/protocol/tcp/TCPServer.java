package com.fja.protocol.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//服务端基本用法：
public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//1.建立TCP服务端，服务端是等待客户端链接过来，因此不需要传入IP对象，只需要监听端口即可
			//【注意】当一个端口被服务端绑定后，如果再想建立一个服务端套接字绑定同样的端口，则会报错BindException
			serverSocket = new ServerSocket(9090);
			//2.接受客户端的链接，这时候accept()方法就会返回一个Socket对象。
			//【注意】accept方法是一个阻塞型的方法，没有客户端与其链接，就会一直等待
			Socket socket = serverSocket.accept();
			//3.获取字节输入流对象，读取客户端发送过来的内容
			InputStream is = socket.getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			len = is.read(buf);
			System.out.println(new String(buf,0,len));
			//获取字节输出流，向客户端返回数据
			OutputStream os = socket.getOutputStream();
			os.write("Hello Client".getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			//4.关闭服务端套接字
			try {
				if(serverSocket!=null)serverSocket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

/**
 * 问： 为什么 ServerSocket没有getOutputStream()或是getInputStream()
 * 答： 因为当有多个客户端链接到服务器的时候，输出流或者输入流具体要读写那个客户端的信息是不明确的，
 * 	      因此必须要用accept()返回相连接的客户端Socket，这样客户端接收到的服务器返回数据才不会出错
 */
