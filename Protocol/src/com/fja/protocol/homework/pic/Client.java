package com.fja.protocol.homework.pic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket soc = new Socket(InetAddress.getLocalHost(),9090);
		InputStream is = soc.getInputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		FileOutputStream fos = new FileOutputStream("E:/2.jpg");
		while(true){
			len = is.read(buf);
			fos.write(buf,0,len);
		}
	}
}
