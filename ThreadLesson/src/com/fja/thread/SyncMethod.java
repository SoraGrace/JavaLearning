package com.fja.thread;
/**
 * 演示同步函数：
 * 同步代码块和同步函数推荐使用同步代码块
 * 原因：
 * 		1). 同步代码块的锁可以自定义，而同步函数的锁是固定的
 *      2). 同步代码块的同步范围可以自定义，同步函数内的代码不管是否有线程安全的问题
 *      	都会同步，因此对性能是损耗。 
 */
public class SyncMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankThread t0 = new BankThread("husband");
		BankThread t1 = new BankThread("wife");
		t0.start();
		t1.start();
	}

}

class BankThread extends Thread{
	static int balance = 10000;			//一万元余额
	public BankThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getMoney();
	}
	
	/**
	 * 静态函数的锁对象是函数所属类的字节码文件对象(BankThread.class),类的字节码文件对象处于
	 * 方法区，唯一且被所有对象共享。
	 * 
	 * 如果是非静态的同步函数，那么锁对象就是拥有方法的对象本身，也就是this，在InterruptThread中有体现。
	 */
	public static synchronized void getMoney(){
		while(true){
			if(balance>0){
				System.out.println(Thread.currentThread().getName()+"取走了1000块，还剩余"+(balance-1000)+"元");
				balance -= 1000;
			}else{
				System.out.println("余额被取完了");
				break;
			}
		}
	}
}