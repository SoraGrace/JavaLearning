package com.fja.thread;
/**
 * �ػ��߳�(��̨�߳�)�� 
 * ��һ�����������ֻʣ�����ػ��̣߳���ô�ػ��߳�Ҳ��������
 * �Զ�����߳�Ĭ�϶������ػ��߳�
 * 
 * join������
 * 	һ���߳����ִ����join��䣬��ô�����µ��߳��̼߳��룬ִ�и������̱߳����ò����¼�����߳�
 * 	�ȴ��¼�����߳�ִ����������룬Ȼ���ټ���ִ���Լ�δ��ɵ�������롣 
 */
public class DaemonThread {
	public static void main(String[] args) {
		Daemon d = new Daemon("�ػ��߳�");
		d.setDaemon(true);			//���߳�����Ϊ�ػ��߳�
		if(d.isDaemon()){
			System.out.println("���ػ��߳�");
		}else{
			System.out.println("�����ػ��߳�");
		}
		
		Mother m = new Mother("Χȹ����");
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
				System.out.println(currentThread().getName()+":����"+i+"%");
			else
				System.out.println(currentThread().getName()+":�������׼����װ");
		}
	}
}


/**
 * ��ʾjoin()����  
 */
class Mother extends Thread{
	public Mother(String name){
		super(name);
	}
	
	@Override
	public void run() {
		String name = currentThread().getName();
		System.out.println(name+"ϴ��");
		System.out.println(name+"�в�");
		System.out.println(name+"׼�����ˣ�����û�н�����");
		//��ʼִ��join()������Ҫע�⣬����Ĵ�����Motherʵ�ֶ�����ִ�е�
		Son s = new Son("��ͷ����");
		s.start();				//������ִ��start()
		try {
			s.join();			//Motherʵ�ֶ����ó�CPUִ��Ȩ
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name+"�յ����ͣ���������");
		System.out.println("�˳�����");
		
	}
}

class Son extends Thread{
	public Son(String name){
		super(name);
	}
	@Override
	public void run() {
		String name = currentThread().getName();
		System.out.println(name+"��¥");
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name+"�ߵ��˳���");
		System.out.println(name+"���꽴�ͣ�������");
		System.out.println(name+"���ң���������");
	}
	
}
