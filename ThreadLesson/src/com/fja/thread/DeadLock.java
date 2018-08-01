package com.fja.thread;
/**
 * 死锁现象：
 * 出现的根本原因
 * 1). 存在两个或者两个以上的线程
 * 2). 存在两个或者两个以上的共享资源
 * 
 * 死锁现象无法解决，只能去避免。
 */
public class DeadLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread t0 = new MyThread("铁蛋");
		MyThread t1 = new MyThread("狗剩");
		t0.start();
		t1.start();
		//有一定几率铁蛋拿到遥控器，狗剩拿到电池，双方相互等待对方释放锁，从而造成死锁现象。
	}
}


class MyThread extends Thread{
	public MyThread(String name){
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if("铁蛋".equals(currentThread().getName())){
			synchronized("遥控器"){
				System.out.println("铁蛋拿到了遥控器，准备去拿电池！！");
				synchronized("电池"){
					System.out.println("铁蛋拿到了遥控器和电池，成功启动了空调。");
				}
			}
		}else if("狗剩".equals(currentThread().getName())){
			synchronized("电池"){
				System.out.println("狗剩拿到了电池，准备去拿遥控器！！");
				synchronized("遥控器"){
					System.out.println("狗剩拿到了遥控器和电池，成功启动了空调。");
				}
			}
		}
	}
}