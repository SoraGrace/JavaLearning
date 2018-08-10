package com.fja.thread;
/**
 * 线程间通讯： 一个线程完成了自己的任务时，要通知另一个线程去完成另外一个任务
 * 经典案例：生产者与消费者 
 * 生产者不断的生产产品，消费者不断地消费产品，产品是两者共享的。
 * 
 * 线程间的通信需要用到两个方法
 * 
 * wait():   如果线程执行了wait方法，该线程会进入等待状态，等待状态下的线程必须要被其他线程调用notify方法才能唤醒
 * 
 * notify(): 唤醒等待的线程
 * 
 * wait()和notify()方法的使用要注意的事项：
 * 1). wait()和notify()方法是继承自Object类的
 * 2). wait()和notify()方法必须要在同步代码块或者同步函数中使用。
 * 3). wait()和notify()方法必须由锁对象调用。
 * 
 * 问：为什么wait()和notify()方法不是Thread而是Object类的？
 * 答：因为锁对象可以是任何线程之间共享的对象。
 * 
 * 问：为什么wait()和notify()方法必须要在同步代码块或者同步函数中使用
 * 答：因为这两个方法必须要锁对象去调用，没有同步代码块和同步函数就没有锁的概念。
 * 
 * 问：为什么wait()和notify()方法必须由锁对象调用
 * 答：需要以锁对象为标识符建立一个线程池。
 * 
 * notifyAll()：唤醒线程池中所有等待的线程。
 */
public class Communication {

	public static void main(String[] args) {
		Product product = new Product();
		Producer p = new Producer("生产者", product);
		Customer c = new Customer("消费者", product);
		p.start();
		c.start();
	}

}

class Product{
	
	String name;
	
	double price;
	
	boolean flag = false;
	
}

class Producer extends Thread{
	
	Product p;			//产品
	
	public Producer(String name,Product p){
		super(name);
		this.p = p; 
	}

	@Override
	public void run() {
		int i = 0;
		while(true){
			synchronized(p){			//使用两个线程都能访问到的对象作为锁对象，解决线程安全问题
				if(!p.flag){
					if(i%2==0){
						p.name = "苹果";
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.price = 6.5;
					}else{
						p.name = "香蕉";
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.price = 2.0;
					}
					System.out.println(currentThread().getName()+"生产了"+p.name+",价格是"+p.price+"元");
					p.flag = true;
					i++;
					
					//如果一个线程执行了notify()方法，那么就会唤醒以锁对象为标识符的线程池中的等待的线程的其中一个。
					p.notify();
				}else if(p.flag){
					try {
						//一个线程如果执行了wait()方法，那么该线程就会进入一个以锁对象为标识符的线程池中等待。并且释放锁对象 
						p.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}


//消费者
class Customer extends Thread{
	
	Product p;
	
	public Customer(String name,Product p){
		super(name);
		this.p = p; 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			synchronized(p){
				if(p.flag){
					System.out.println(currentThread().getName()+"消费了"+p.name+",价格是"+p.price+"元");
					p.flag = false;
					p.notify();
				}else if(!p.flag){//如果消费者线程执行到这里，判断flag是false，产品还没有被生产，则进入线程池，等待生产者生产完产品后唤醒。
					try {
						p.wait();		
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}
	
}