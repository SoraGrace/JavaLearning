package com.fja.thread;
/**
 * 线程的常用方法：
 * Thread(String name) 			初始化线程的名字(构造器)
 * getName()					返回线程的名字
 * setName(String name)			设置线程对象的名字
 * sleep()						线程睡眠指定的毫秒数
 * getPriority()				返回当前线程对象的优先级，默认是5
 * setPriority(int newPriority)	设置线程的优先级
 * currentThread()				静态方法，哪个线程执行了这个方法，就返回哪个对象
 */
public class ThreadMethod extends Thread{
	//要使用Thread(String name)，需要显示的调用父类的有参构造函数。
	public ThreadMethod(String name){
		super(name);
	}
	public ThreadMethod(){};
	@Override
	public void run() {
		/**
		 *  问：为什么sleep()在方法声明时throws InterruptedExecption会报错
		 *     但是使用try/catch捕获就没有问题。
		 * 	答：因为这里的run()方法是重写父类的run()方法，子类的重写的方法抛出的异常
		 * 	       一定要小于或者等于父类抛出的异常。在父类的run()方法中是没有抛出异常的,因此这里
		 * 	       只能用 try/catch进行捕获。
		 * 	ps.父类是良民(没有抛出异常)，子类就不能偷鸡摸狗，也不能杀人放火，只能是良民，
		 * 	       父类偷鸡摸狗，则子类允许是良民或者偷鸡摸狗，但是不允许杀人放火
		 */
		//sleep(1000);
		/** 
		 * 问： 这里输出true还是false？ 
		 * 答：是true，currentThread()方法谁调用就返回谁
		 */
		System.out.println(this==currentThread());
		for (int i = 0; i < 100; i++) {
			System.out.println(this.getName()+": "+i);
		}
	}

	public static void main(String[] args) {
		//Thread(String name)
		ThreadMethod t0 = new ThreadMethod("线程-构造器设置名字");
		//getName()
		System.out.println("线程的名字："+t0.getName());
		
		//setName(String name)
		ThreadMethod t1 = new ThreadMethod();
		t1.setName("线程-setName方法设置名字");
		System.out.println("线程的名字："+t1.getName());
		
		//优先级默认是5
		System.out.println("t0线程的优先级： "+t0.getPriority());
		System.out.println("main线程的优先级： "+Thread.currentThread().getPriority());
		
		//数字越大，优先级越高(10最大)
		/**
		 * 问：设置了t0线程的优先级最大后，t0一定能比main线程先执行完任务代码么？
		 * 答：不一定，提高优先级，只是提高了抢占CPU执行权的概率 
		 */
		t0.setPriority(10);
		
		//【注意】sleep是一个静态方法,【重点】谁执行了sleep谁就睡眠
		try {
			/**
			 * 问：t0.sleep()执行之后到底是哪个线程在睡眠，是t0睡眠么？
			 * 答：睡眠的是主线程，sleep是一个static方法，因此t0.sleep()其实和t0没有关系，
			 * t0.sleep()和下方的sleep()效果相同，执行sleep()的是主线程，因此睡眠的线程也是主线程
			 */
			t0.sleep(2000);
			t0.start();
			for (int i = 0; i < 100; i++) {
				System.out.println("主线程: "+i);
			}
			
			sleep(5000);
			for (int i = 100; i > 0; i--) {
				System.out.println("主线程:"+i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
