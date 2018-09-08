package com.fja.protocol.homework.pic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(9090);
		System.out.println("===server launch===");
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
		File file=new File("D:/2.jpg");
		FileInputStream fis = null;
		try {
			BufferedOutputStream bos = new BufferedOutputStream(soc.getOutputStream());
			fis = new FileInputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while(true){
				len = fis.read(buf);
				if(len==-1)break;
				bos.write(buf,0,len);
				bos.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fis!=null)fis.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}