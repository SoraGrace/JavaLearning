package com.fja.thread;
/**
 * 中断线程：
 * 1). 停止一个线程，一般会通过一个变量去控制。
 * 2). 如果需要停止一个等待状态下的线程，我们需要通过变量配合notify()或者interrupt()来使用。
 * 
 * notify()和interrupt()的区别
 * 1).notify()方法唤醒线程比较温柔，interrupt()唤醒线程比较粗暴，强制清除指定线程的等待状态，线程会收到一个异常
 * 2).notify()方法是锁对象调用的而interrupt()是线程对象调用的，也就是说notify()唤醒的线程是不确定的，
 * 	  interrupt()唤醒的就是调用该方法的线程。
 */
public class InterruptThread extends Thread{
	boolean flag = true;
	public InterruptThread(String name){
		super(name);
	}
	
	public static void main(String[] args) throws InterruptedException {
		InterruptThread t = new InterruptThread("狗娃");
		t.setPriority(10);
		t.start();
		for (int i = 0; i < 100; ) {
			synchronized(t){
				sleep(20);
				i++;
				System.out.println(currentThread().getName()+":"+i);
				//在50的时候终止狗娃线程
				if(i==50){
					//t.notify();			//如果没有notify()或者interrupt()狗娃线程将一直等待，程序无法结束。
					t.interrupt();
					t.flag = false;
				}
			}
		}
	}

	@Override
	public void run() {
		int i = 0;
		while(true){
			synchronized(this){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();			//这里会接收到interrupt()方法强制中断线程的异常
					System.out.println(currentThread().getName()+"被强制终止了");
				}
				if(flag){
					i++;
					System.out.println(currentThread().getName()+":"+i);
				}else{
					break;
				}
			}
		}
	}
}
