package com.fja.thread;
/**
 * �ж��̣߳�
 * 1). ֹͣһ���̣߳�һ���ͨ��һ������ȥ���ơ�
 * 2). �����Ҫֹͣһ���ȴ�״̬�µ��̣߳�������Ҫͨ���������notify()����interrupt()��ʹ�á�
 * 
 * notify()��interrupt()������
 * 1).notify()���������̱߳Ƚ����ᣬinterrupt()�����̱߳Ƚϴֱ���ǿ�����ָ���̵߳ĵȴ�״̬���̻߳��յ�һ���쳣
 * 2).notify()��������������õĶ�interrupt()���̶߳�����õģ�Ҳ����˵notify()���ѵ��߳��ǲ�ȷ���ģ�
 * 	  interrupt()���ѵľ��ǵ��ø÷������̡߳�
 */
public class InterruptThread extends Thread{
	boolean flag = true;
	public InterruptThread(String name){
		super(name);
	}
	
	public static void main(String[] args) throws InterruptedException {
		InterruptThread t = new InterruptThread("����");
		t.setPriority(10);
		t.start();
		for (int i = 0; i < 100; ) {
			synchronized(t){
				sleep(20);
				i++;
				System.out.println(currentThread().getName()+":"+i);
				//��50��ʱ����ֹ�����߳�
				if(i==50){
					//t.notify();			//���û��notify()����interrupt()�����߳̽�һֱ�ȴ��������޷�������
					t.interrupt();
					t.flag = false;
				}
			}
		}
	}

	@Override
	public void run() {
		int i = 0;
		while(true){
			synchronized(this){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();			//�������յ�interrupt()����ǿ���ж��̵߳��쳣
					System.out.println(currentThread().getName()+"��ǿ����ֹ��");
				}
				if(flag){
					i++;
					System.out.println(currentThread().getName()+":"+i);
				}else{
					break;
				}
			}
		}
	}
}
