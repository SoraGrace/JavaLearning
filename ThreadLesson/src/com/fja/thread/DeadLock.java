package com.fja.thread;
/**
 * ��������
 * ���ֵĸ���ԭ��
 * 1). �������������������ϵ��߳�
 * 2). �������������������ϵĹ�����Դ
 * 
 * ���������޷������ֻ��ȥ���⡣
 */
public class DeadLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread t0 = new MyThread("����");
		MyThread t1 = new MyThread("��ʣ");
		t0.start();
		t1.start();
		//��һ�����������õ�ң��������ʣ�õ���أ�˫���໥�ȴ��Է��ͷ������Ӷ������������
	}
}


class MyThread extends Thread{
	public MyThread(String name){
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if("����".equals(currentThread().getName())){
			synchronized("ң����"){
				System.out.println("�����õ���ң������׼��ȥ�õ�أ���");
				synchronized("���"){
					System.out.println("�����õ���ң�����͵�أ��ɹ������˿յ���");
				}
			}
		}else if("��ʣ".equals(currentThread().getName())){
			synchronized("���"){
				System.out.println("��ʣ�õ��˵�أ�׼��ȥ��ң��������");
				synchronized("ң����"){
					System.out.println("��ʣ�õ���ң�����͵�أ��ɹ������˿յ���");
				}
			}
		}
	}
}