package com.fja.thread;
/**
 * �����̵߳ĵڶ��ַ�����
 * 	1). �Զ���һ����ʵ��Runnable�ӿ�
 *  2). ʵ��Runnable�ӿڵ�run()���������̵߳���������run()������
 *  3). ����Runnableʵ����Ķ���
 *  4). ����Thread��Ķ��󣬲��Ұ�Runnableʵ����Ķ��󴫵ݸ�Thread�Ĺ�����
 *  5). ����Thread�����start()�����߳� 
 *  
 *  �ʣ�����Runnableʵ����Ķ������̶߳���ô��
 *  �𣺲����̶߳���ֻ������ʵ����Runnable�ӿڵĶ�����ѣ�ֻ��Thread�����Thread������Ķ�������̶߳���
 *  
 *  �ʣ�ΪʲôҪ��Runnableʵ����Ķ�����Ϊʵ�δ��ݸ�Thread����������ʲô?
 *  �����þ��ǰ�Runnableʵ����Ķ����run()������Ϊ�̵߳��������ִ�С�(������·�Դ��)
 *  
 *  ���ִ����̵߳ķ����У��Ƽ�ʹ�õڶ���
 *  ԭ����Ϊjava�ǵ��̳С���ʵ�ֵġ��ӿ�Ҫ���ܶ࣬��ʵ��Runnable�ӿڵ�ͬʱ�����Լ̳��������ࡣ
 */
public class ImpRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyRunnable r0 = new MyRunnable();
		Thread t0 = new Thread(r0,"����");
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
		 * �ʣ� Thread.currentThread()���صĶ����Ƿ����this
		 * �𣺲����ڣ�Thread.currentThread()���ص���t0����thisָ�����r0
		 */
		System.out.println(Thread.currentThread());
		System.out.println(this);
	}
}


/*Thread��run()������Դ�룺
 	@Override
    public void run() {
        if (target != null) {		//���target�������Ǵ���ȥ��Runnableʵ����Ķ���
            target.run();
        }
    }
 */
