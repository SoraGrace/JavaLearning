package com.fja.homework;
/**
 * ��ҵ��
 * ��һ��ˮ�أ�ˮ�ص������ǹ̶���500L��һ���ǽ�ˮ�ڣ�һ���ǳ�ˮ�ڡ�Ҫ���ˮ���ˮ����ͬʱ���С�
 * ˮ��һ����ˮ�˲��ܼ���עˮ��һ���ſ��ˣ������Լ�����ˮ����ˮ���ٶ���5L/s,��ˮ���ٶ���2L/s�� 
 */
public class Homework {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pool p = new Pool();
		Affusion a = new Affusion("עˮ�߳�", p);
		Discharge d = new Discharge("��ˮ�߳�", p);
		a.start();
		d.start();
	}
}

//עˮ�߳�
class Affusion extends Thread{
	Pool p;
	
	public Affusion(String name,Pool p){
		super(name);
		this.p = p;
	}
	
	@Override
	public void run() {
		//��ͣ��עˮ
		while(true){
			synchronized(p){
				if(p.water == 500){		//�ж�ˮ�ص�ˮ�Ƿ�ע��
					try {
						p.notify();     //���ѷ�ˮ�߳�
						p.wait();		//�����̳߳�����
					} catch (InterruptedException e) {
						e.printStackTrace();
					}			
				}else{
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.water += 5;	//ÿ��5L
					System.out.println(currentThread().getName()+":Ŀǰˮ���е�ˮ��"+p.water+"L");
				}
			}
		}
	}
	
}

//��ˮ�߳�
class Discharge extends Thread{
	Pool p;
	
	public Discharge(String name,Pool p){
		super(name);
		this.p = p;
	}
	
	@Override
	public void run() {
		//��ͣ�ķ�ˮ
		while(true){
			synchronized(p){
				if(p.water > 0){		//�ж�ˮ�ص�ˮ�Ƿ�ע��
					try {
						sleep(1000);	//ÿ��2L
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p.water -= 2;
					System.out.println(currentThread().getName()+":Ŀǰˮ���е�ˮ��"+p.water+"L");
				}else{
					try {
						p.notify();     //���ѷ�ˮ�߳�
						p.wait();		//�����̳߳�����
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}

//ˮ��
class Pool {
	int water = 0;
}