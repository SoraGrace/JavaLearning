package com.fja.thread;
/**
 * ģ��QQ��Ƶ������ͬʱִ��
 */
public class SimulateQQ {
	public static void main(String[] args) {
		//��������߳�������Դ
		Talk t = new Talk();
		t.start();
		
		Video v = new Video();
		v.start();
	}
}

class Video extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("��Ƶ��Ƶ��Ƶ��Ƶ");
		}
	}
}

class Talk extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("hi,��ã�");
		}
	}
}