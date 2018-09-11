package com.fja.protocol.homework.register;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws Exception{
		Socket soc = new Socket(InetAddress.getLocalHost(),9090);
		BufferedReader reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		String serverResponse = null;
		new keyboardThread(soc).start();
		do{
			serverResponse = reader.readLine();
			System.out.println(serverResponse);
		}while(serverResponse!=null);
	}
}

class keyboardThread extends Thread{
	private Socket soc;

	public keyboardThread(Socket soc) {
		super();
		this.soc = soc;
	}

	@Override
	public void run() {
		BufferedReader keyboardReader = null;
		BufferedWriter writer = null;
		try {
			keyboardReader = new BufferedReader(new InputStreamReader(System.in));
			writer = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
			while(true){
				String inputContnt = keyboardReader.readLine();
				writer.write(inputContnt+"\r\n");
				writer.flush();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}