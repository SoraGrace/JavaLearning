package com.fja.thread;
/**
 * �̵߳ĳ��÷�����
 * Thread(String name) 			��ʼ���̵߳�����(������)
 * getName()					�����̵߳�����
 * setName(String name)			�����̶߳��������
 * sleep()						�߳�˯��ָ���ĺ�����
 * getPriority()				���ص�ǰ�̶߳�������ȼ���Ĭ����5
 * setPriority(int newPriority)	�����̵߳����ȼ�
 * currentThread()				��̬�������ĸ��߳�ִ��������������ͷ����ĸ�����
 */
public class ThreadMethod extends Thread{
	//Ҫʹ��Thread(String name)����Ҫ��ʾ�ĵ��ø�����вι��캯����
	public ThreadMethod(String name){
		super(name);
	}
	public ThreadMethod(){};
	@Override
	public void run() {
		/**
		 *  �ʣ�Ϊʲôsleep()�ڷ�������ʱthrows InterruptedExecption�ᱨ��
		 *     ����ʹ��try/catch�����û�����⡣
		 * 	����Ϊ�����run()��������д�����run()�������������д�ķ����׳����쳣
		 * 	       һ��ҪС�ڻ��ߵ��ڸ����׳����쳣���ڸ����run()��������û���׳��쳣��,�������
		 * 	       ֻ���� try/catch���в���
		 * 	ps.����������(û���׳��쳣)������Ͳ���͵��������Ҳ����ɱ�˷Ż�ֻ��������
		 * 	       ����͵���������������������������͵�����������ǲ�����ɱ�˷Ż�
		 */
		//sleep(1000);
		/** 
		 * �ʣ� �������true����false�� 
		 * ����true��currentThread()����˭���þͷ���˭
		 */
		System.out.println(this==currentThread());
		for (int i = 0; i < 100; i++) {
			System.out.println(this.getName()+": "+i);
		}
	}

	public static void main(String[] args) {
		//Thread(String name)
		ThreadMethod t0 = new ThreadMethod("�߳�-��������������");
		//getName()
		System.out.println("�̵߳����֣�"+t0.getName());
		
		//setName(String name)
		ThreadMethod t1 = new ThreadMethod();
		t1.setName("�߳�-setName������������");
		System.out.println("�̵߳����֣�"+t1.getName());
		
		//���ȼ�Ĭ����5
		System.out.println("t0�̵߳����ȼ��� "+t0.getPriority());
		System.out.println("main�̵߳����ȼ��� "+Thread.currentThread().getPriority());
		
		//����Խ�����ȼ�Խ��(10���)
		/**
		 * �ʣ�������t0�̵߳����ȼ�����t0һ���ܱ�main�߳���ִ�����������ô��
		 * �𣺲�һ����������ȼ���ֻ���������ռCPUִ��Ȩ�ĸ��� 
		 */
		t0.setPriority(10);
		
		//��ע�⡿sleep��һ����̬����,���ص㡿˭ִ����sleep˭��˯��
		try {
			/**
			 * �ʣ�t0.sleep()ִ��֮�󵽵����ĸ��߳���˯�ߣ���t0˯��ô��
			 * ��˯�ߵ������̣߳�sleep��һ��static���������t0.sleep()��ʵ��t0û�й�ϵ��
			 * t0.sleep()���·���sleep()Ч����ͬ��ִ��sleep()�������̣߳����˯�ߵ��߳�Ҳ�����߳�
			 */
			t0.sleep(2000);
			t0.start();
			for (int i = 0; i < 100; i++) {
				System.out.println("���߳�: "+i);
			}
			
			sleep(5000);
			for (int i = 100; i > 0; i--) {
				System.out.println("���߳�:"+i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
