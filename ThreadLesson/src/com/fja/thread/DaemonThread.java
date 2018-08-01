package com.fja.thread;
/**
 * 守护线程(后台线程)： 
 * 在一个进程中如果只剩下了守护线程，那么守护线程也会死亡。
 * 自定义的线程默认都不是守护线程
 * 
 * join方法：
 * 	一个线程如果执行了join语句，那么就有新的线程线程加入，执行该语句的线程必须让步给新加入的线程
 * 	等待新加入的线程执行完任务代码，然后再继续执行自己未完成的人物代码。 
 */
public class DaemonThread {
	public static void main(String[] args) {
		Daemon d = new Daemon("守护线程");
		d.setDaemon(true);			//将线程设置为守护线程
		if(d.isDaemon()){
			System.out.println("是守护线程");
		}else{
			System.out.println("不是守护线程");
		}
		
		Mother m = new Mother("围裙妈妈");
		m.start();
	}
}

class Daemon extends Thread{
	public Daemon(String name){
		super(name);
	}
	@Override
	public void run() {
		for (int i = 1; i <=100; i++) {
			if(i<100)
				System.out.println(currentThread().getName()+":下载"+i+"%");
			else
				System.out.println(currentThread().getName()+":下载完毕准备安装");
		}
	}
}


/**
 * 演示join()方法  
 */
class Mother extends Thread{
	public Mother(String name){
		super(name);
	}
	
	@Override
	public void run() {
		String name = currentThread().getName();
		System.out.println(name+"洗菜");
		System.out.println(name+"切菜");
		System.out.println(name+"准备炒菜，发现没有酱油了");
		//开始执行join()，这里要注意，这里的代码是Mother实现对象来执行的
		Son s = new Son("大头儿子");
		s.start();				//别忘了执行start()
		try {
			s.join();			//Mother实现对象让出CPU执行权
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name+"收到酱油，继续炒菜");
		System.out.println("菜炒完了");
		
	}
}

class Son extends Thread{
	public Son(String name){
		super(name);
	}
	@Override
	public void run() {
		String name = currentThread().getName();
		System.out.println(name+"下楼");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name+"走到了超市");
		System.out.println(name+"买完酱油，往回走");
		System.out.println(name+"到家，交出酱油");
	}
	
}
