package com.fja.thread;
/**
 * 创建线程的第二种方法：
 * 	1). 自定义一个类实现Runnable接口
 *  2). 实现Runnable接口的run()方法，把线程的任务定义在run()方法上
 *  3). 创建Runnable实现类的对象
 *  4). 创建Thread类的对象，并且把Runnable实现类的对象传递给Thread的构造器
 *  5). 调用Thread对象的start()启动线程 
 *  
 *  问：请问Runnable实现类的对象是线程对象么？
 *  答：不是线程对象，只不过是实现了Runnable接口的对象而已，只有Thread类或者Thread的子类的对象才是线程对象。
 *  
 *  问：为什么要把Runnable实现类的对象作为实参传递给Thread对象，作用是什么?
 *  答：作用就是把Runnable实现类的对象的run()方法作为线程的任务代码执行。(详见最下方源码)
 *  
 *  两种创建线程的方法中，推荐使用第二种
 *  原因：因为java是单继承、多实现的。接口要灵活很多，在实现Runnable接口的同时，可以继承其他的类。
 */
public class ImpRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRunnable r0 = new MyRunnable();
		Thread t0 = new Thread(r0,"铁蛋");
		t0.start();
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName()+": "+i);
		}
	}
}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName()+": "+i);
		}
		
		/**
		 * 问： Thread.currentThread()返回的对象是否等于this
		 * 答：不等于，Thread.currentThread()返回的是t0，而this指向的是r0
		 */
		System.out.println(Thread.currentThread());
		System.out.println(this);
	}
}


/*Thread的run()方法的源码：
 	@Override
    public void run() {
        if (target != null) {		//这和target就是我们传进去的Runnable实现类的对象
            target.run();
        }
    }
 */
