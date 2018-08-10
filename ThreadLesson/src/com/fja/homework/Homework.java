package com.fja.homework;
/**
 * 作业：
 * 有一个水池，水池的容量是固定的500L，一边是进水口，一边是出水口。要求进水与防水不能同时进行。
 * 水池一旦满水了不能继续注水，一旦放空了，不可以继续放水。进水的速度是5L/s,放水的速度是2L/s。 
 */
public class Homework {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pool p = new Pool();
		Affusion a = new Affusion("注水线程", p);
		Discharge d = new Discharge("放水线程", p);
		a.start();
		d.start();
	}
}

//注水线程
class Affusion extends Thread{
	Pool p;
	
	public Affusion(String name,Pool p){
		super(name);
		this.p = p;
	}
	
	@Override
	public void run() {
		//不停的注水
		while(true){
			synchronized(p){
				if(p.water == 500){		//判断水池的水是否注满
					try {
						p.notify();     //唤醒放水线程
						p.wait();		//进入线程池休眠
					} catch (InterruptedException e) {
						e.printStackTrace();
					}			
				}else{
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.water += 5;	//每秒5L
					System.out.println(currentThread().getName()+":目前水池中的水是"+p.water+"L");
				}
			}
		}
	}
	
}

//放水线程
class Discharge extends Thread{
	Pool p;
	
	public Discharge(String name,Pool p){
		super(name);
		this.p = p;
	}
	
	@Override
	public void run() {
		//不停的防水
		while(true){
			synchronized(p){
				if(p.water > 0){		//判断水池的水是否注满
					try {
						sleep(1000);	//每秒2L
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.water -= 2;
					System.out.println(currentThread().getName()+":目前水池中的水是"+p.water+"L");
				}else{
					try {
						p.notify();     //唤醒放水线程
						p.wait();		//进入线程池休眠
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}

//水池
class Pool {
	int water = 0;
}