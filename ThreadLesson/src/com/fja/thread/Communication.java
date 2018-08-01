package com.fja.thread;
/**
 * �̼߳�ͨѶ�� һ���߳�������Լ�������ʱ��Ҫ֪ͨ��һ���߳�ȥ�������һ������
 * ���䰸������������������ 
 * �����߲��ϵ�������Ʒ�������߲��ϵ����Ѳ�Ʒ����Ʒ�����߹���ġ�
 * 
 * �̼߳��ͨ����Ҫ�õ���������
 * 
 * wait():   ����߳�ִ����wait���������̻߳����ȴ�״̬���ȴ�״̬�µ��̱߳���Ҫ�������̵߳���notify�������ܻ���
 * 
 * notify(): ���ѵȴ����߳�
 * 
 * wait()��notify()������ʹ��Ҫע������
 * 1). wait()��notify()�����Ǽ̳���Object���
 * 2). wait()��notify()��������Ҫ��ͬ����������ͬ��������ʹ�á�
 * 3). wait()��notify()������������������á�
 * 
 * �ʣ�Ϊʲôwait()��notify()��������Thread����Object��ģ�
 * ����Ϊ������������κ��߳�֮�乲��Ķ���
 * 
 * �ʣ�Ϊʲôwait()��notify()��������Ҫ��ͬ����������ͬ��������ʹ��
 * ����Ϊ��������������Ҫ������ȥ���ã�û��ͬ��������ͬ��������û�����ĸ��
 * 
 * �ʣ�Ϊʲôwait()��notify()�������������������
 * ����Ҫ��������Ϊ��ʶ������һ���̳߳ء�
 * 
 * notifyAll()�������̳߳������еȴ����̡߳�
 */
public class Communication {

	public static void main(String[] args) {
		Product product = new Product();
		Producer p = new Producer("������", product);
		Customer c = new Customer("������", product);
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
	
	Product p;			//��Ʒ
	
	public Producer(String name,Product p){
		super(name);
		this.p = p; 
	}

	@Override
	public void run() {
		int i = 0;
		while(true){
			synchronized(p){			//ʹ�������̶߳��ܷ��ʵ��Ķ�����Ϊ�����󣬽���̰߳�ȫ����
				if(!p.flag){
					if(i%2==0){
						p.name = "ƻ��";
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.price = 6.5;
					}else{
						p.name = "�㽶";
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						p.price = 2.0;
					}
					System.out.println(currentThread().getName()+"������"+p.name+",�۸���"+p.price+"Ԫ");
					p.flag = true;
					i++;
					
					//���һ���߳�ִ����notify()��������ô�ͻỽ����������Ϊ��ʶ�����̳߳��еĵȴ����̵߳�����һ����
					p.notify();
				}else if(p.flag){
					try {
						//һ���߳����ִ����wait()��������ô���߳̾ͻ����һ����������Ϊ��ʶ�����̳߳��еȴ��������ͷ������� 
						p.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}


//������
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
					System.out.println(currentThread().getName()+"������"+p.name+",�۸���"+p.price+"Ԫ");
					p.flag = false;
					p.notify();
				}else if(!p.flag){//����������߳�ִ�е�����ж�flag��false����Ʒ��û�б�������������̳߳أ��ȴ��������������Ʒ���ѡ�
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