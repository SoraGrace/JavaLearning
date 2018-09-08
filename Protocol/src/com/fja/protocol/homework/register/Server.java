package com.fja.protocol.homework.register;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 需求：
 * 实现登陆与注册功能。
 * 客户端与服务器连接时，提示客户端选择。
 * 注册：客户端将用户名密码发送给服务端，服务端把数据保存在服务器本地文件。
 * 登陆：客户端将用户名和密码发送给服务端，服务端校验返回结果给客户端。 
 */
public class Server {
	public static void main(String[] args) {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(9090);
			while(true){
				Socket soc = ss.accept();
				new ServerThread(soc).start();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}


class ServerThread extends Thread{
	private Socket soc;

	public ServerThread(Socket soc) {
		super();
		this.soc = soc;
	}

	@Override
	public void run() {
		
	}
}