package com.fja.thread;
/**
 * 什么是进程：
 * 正在执行的一个程序被称为一个进程，进程负责了内存空间的分配。
 * 
 * 什么是线程：
 * 线程在一个进程中负责了代码的执行，就是进程中一个执行路径。
 * 
 * 什么是多线程：
 * 就是在一个进程中，有多个线程同一时间段里执行不同代码。  
 * 
 * 注意：
 * 运行任何一个java程序，jvm在运行的时候都会创建一个main线程执行main方法中的所有代码。
 * 
 * 问：一个java应用程序至少有几个线程？
 * 答：至少有两个线程，一个是主线程负责main方法代码执行，一个是垃圾回收器线程，负责垃圾回收。
 * 
 * 问：多线程是同时执行任务的么？
 * 答：如果是单核cpu则不是同时。因为单核cpu在一个时间片段中只能执行一段代码。因此，不同的线程会不停的抢夺cpu资源。 
 * 
 * 多线程的好处：
 * 1).解决了一个进程同时段执行多个任务的问题。
 * 2).提高了资源的利用率(资源还有富裕的时候，可以给其他线程执行其他任务)。
 * 
 * 多线程的弊端：
 * 1).增加了cpu的负担
 * 2).降低一个进程中线程的执行概率
 * 3).引发线程安全问题
 * 4).出现死锁现象
 * 
 * 创建线程的N种方法：
 * 1).继承Thread类(java.lang.Thread)
 * 2).实现Runnable接口
 */


//继承Thread-->重写run()方法-->调用实例的start()方法启动线程。
public class CreateThread extends Thread{
	@Override
	public void run(){
		/**
		 * 问：为什么要重写run方法？
		 * 答：每个线程都有自己的任务代码，jvm虚拟机创建的主线程的任务代码就是main方法中的所有代码。
		 * 	     自定义线程的任务的代码就是写在run方法中的代码，自定义线程负责了run方法的执行。
		 */
		for(int i = 0;i<100;i++){
			System.out.println("自定义线程："+i);
		}
	}
	
	public static void main(String[] args){
		CreateThread t = new CreateThread();
		//t.run();					//直接执行run()方法，并不会发生cpu资源抢夺
		t.start();					//启动线程，自动执行run()。
		for(int i = 0;i<100;i++){
			System.out.println("main线程："+i);
		}
	}
}


//实现Runnable接口
class $CreateThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
