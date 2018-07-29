package com.fja.thread;
/**
 * 模拟QQ视频和聊天同时执行
 */
public class SimulateQQ {
	public static void main(String[] args) {
		//体会两个线程争夺资源
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
			System.out.println("视频视频视频视频");
		}
	}
}

class Talk extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("hi,你好！");
		}
	}
}