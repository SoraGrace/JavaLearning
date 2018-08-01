package com.fja.thread;
/**
 * 出现线程安全问题的条件：
 * 1). 有多个线程对象
 * 2). 线程之间共享一个资源
 * 3). 任务方法中有多个语句操作共享的资源
 * 
 * 
 * 解决的方法：
 * 1). 同步代码块
 * 	   synchronized(需要上锁的对象){代码}
 * 	       注意：
 * 		锁对象可以是任意的对象(只要是对象就可以)，但是注意这个锁对象必须是被线程共享，唯一的
 * 		在同步代码块中调用了sleep()方法不会释放锁对象
 * 		会影响代码执行的效率，除非真的有线程安全问题，不要使用同步
 * 
 * 2). 同步函数
 * 	        就是用synchronized修饰一个函数
 * 	        同步函数需要注意的事项：
 * 	   1).如果是一个非静态的同步函数，它的锁对象是this；如果是静态的同步函数，锁对象是当前函数所属的类的字节码文件(class对象)
 * 	   2).同步函数的锁对象是固定的，不能由用户指定。
 * 
 * 为什么任意的对象都可以作为锁对象：
 * 因为任意的对象内部都维护了一个状态(非静态成员变量)，java同步机制就是使用了对象中的状态作为锁的标识。
 * 
 */
public class ThreadSafty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Seller s0 = new Seller("售票窗口1");
		Seller s1 = new Seller("售票窗口2");
		Seller s2 = new Seller("售票窗口3");
		s0.start();
		s1.start();
		s2.start();
	}

}

class Seller extends Thread{
	static int ticket_num = 50;
	static Object o = new Object(); //任意的被线程所共享的对象都可以成为锁
	public Seller(String name){
		super(name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			/**
			 * 问： synchronized("锁"){}是否能起到同步锁的作用？
			 * 答：可以，因为"锁"这种形式的字符串是保存在常量池中的，创建之后是唯一且被线程们共享的
			 *    因此，它可以为做为锁对象。但是synchronized(new String("锁")){}这样是不行的，
			 *    因为没执行一次run()方法都回生成一个新的字符串对象，且不被线程所共享
			 */
			//synchronized(o){					//可以同步
			//synchronized("锁"){				//可以同步
			synchronized(new String("锁")){		//不可以同步
				if(ticket_num>0){
					System.out.println(currentThread().getName()+"售出:"+ticket_num);
					ticket_num--;
				}else{
					System.out.println("车票售罄了");
					break;
				}
			}
		}
	}
}