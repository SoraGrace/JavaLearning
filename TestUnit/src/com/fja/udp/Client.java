package com.fja.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//聊天室客户端
public class Client {
	
	public static void main(String[] args) {
		
		try {
			//监听8080端口
			new ReciveThread(new DatagramSocket(8080)).start();
			
			new SendThread(new DatagramSocket()).start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}


class ReciveThread extends Thread{
	
	private DatagramSocket soc;
	
	public ReciveThread(DatagramSocket soc) {
		super();
		this.soc = soc;
	}

	@Override
	public void run() {
		while(true){
			try {
				byte[] buf = new byte[1024];
				DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
				soc.receive(datagramPacket);
				String content = new String(datagramPacket.getData(),0,datagramPacket.getLength(),"utf-8");
				System.out.println(datagramPacket.getAddress().getHostAddress()+": "+content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}


class SendThread extends Thread{
	
	private DatagramSocket soc;
	
	public SendThread(DatagramSocket soc) {
		super();
		this.soc = soc;
	}

	@Override
	public void run() {
		String content = null;
		try {
			while(!"bye".equalsIgnoreCase(content)){
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				content = reader.readLine();
				byte[] array = content.getBytes("utf-8");
				//xxx.xxx.xxx.255是udp的广播地址
				DatagramPacket packet = new DatagramPacket(array, array.length, InetAddress.getByName("192.168.3.255"), 8080);
				soc.send(packet);
			}
			soc.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}