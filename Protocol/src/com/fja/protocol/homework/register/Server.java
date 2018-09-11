package com.fja.protocol.homework.register;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 需求：
 * 实现登陆与注册功能。
 * 客户端与服务器连接时，提示客户端选择。
 * 注册：客户端将用户名密码发送给服务端，服务端把数据保存在服务器本地文件。
 * 登陆：客户端将用户名和密码发送给服务端，服务端校验返回结果给客户端。 
 */
public class Server {

	public static void main(String[] args) throws Exception {
		System.out.println("=====服务器启动=====");
		ServerSocket ss = new ServerSocket(9090);
		while(true){
			Socket soc = ss.accept();
			new ServerThread(soc).start();
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
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream(),"gbk"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream(),"UTF-8"));
			while(true){
				writer.write("请选择功能 a.注册       b.登录 \r\n");
				writer.flush();
				String choice = reader.readLine();
				if("a".equalsIgnoreCase(choice)){
					String account = null;
					while(true){
						writer.write("请输入用户名 \r\n");
						writer.flush();
						account = reader.readLine();
						//验证用户名是否已经存在
						boolean flag = check(account,null);
						if(flag){
							break;
						}else{
							writer.write("用户名已存在 \r\n");
						}
					}
					while(true){
						writer.write("请输入密码 \r\n");
						writer.flush();
						String password = reader.readLine();
						if(password==null){
							writer.write("密码不符合要求 \r\n");
						}else{
							instock(account,password);
							System.out.println("注册成功");
							break;
						}
					}
				}else if("b".equalsIgnoreCase(choice)){
					String account = null;
					while(true){
						writer.write("请输入用户名 \r\n");
						writer.flush();
						account = reader.readLine();
						if(account!=null){
							break;
						}else{
							writer.write("用户名不能为空 \r\n");
						}
					}
					while(true){
						writer.write("请输入密码 \r\n");
						writer.flush();
						String password = reader.readLine();
						if(password==null){
							writer.write("密码不能为空 \r\n");
						}else{
							boolean flag = check(account,password);
							if(flag){
								writer.write("验证成功，欢迎登录"+account+"\r\n");
							}else{
								writer.write("验证失败，请检查用户名、密码后重试\r\n");
							}
							break;
						}
					}
				}else{
					writer.write("无效输入 \r\n");
					writer.flush();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static synchronized boolean check(String account,String password){
		File dir = new File("E:/register");
		FileInputStream fis = null;
		boolean flag = true;
		if(password!=null){
			flag = false;
		}
		try {
			if(!dir.exists()){
				dir.mkdir();
			}
			File file = new File("E:/register/info.properties");
			if(!file.exists()){
				file.createNewFile();
			}
			fis = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fis);
			Set<Object> set = prop.keySet();
			Iterator<Object> it = set.iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = prop.getProperty(key);
				if(key.equals(account)){
					if(password==null){		//注册
						flag = false;
					}else if(password.equals(value)){					//登录
						flag = true;
					}
				}
			}
		} catch (IOException e){
			throw new RuntimeException(e);
		}finally{
			try {
				if(fis!=null)fis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return flag;
	}
	
	private static synchronized void instock(String account,String password){
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("E:/register/info.properties",true);
			Properties prop = new Properties();
			prop.setProperty(account, password);
			prop.store(fos, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fos!=null)fos.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}