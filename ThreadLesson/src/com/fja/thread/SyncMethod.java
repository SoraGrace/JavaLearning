package com.fja.thread;
/**
 * ��ʾͬ��������
 * ͬ��������ͬ�������Ƽ�ʹ��ͬ�������
 * ԭ��
 * 		1). ͬ���������������Զ��壬��ͬ�����������ǹ̶���
 *      2). ͬ��������ͬ����Χ�����Զ��壬ͬ�������ڵĴ��벻���Ƿ����̰߳�ȫ������
 *      	����ͬ������˶���������ġ� 
 */
public class SyncMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankThread t0 = new BankThread("husband");
		BankThread t1 = new BankThread("wife");
		t0.start();
		t1.start();
	}

}

class BankThread extends Thread{
	static int balance = 10000;			//һ��Ԫ���
	public BankThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getMoney();
	}
	
	/**
	 * ��̬�������������Ǻ�����������ֽ����ļ�����(BankThread.class),����ֽ����ļ�������
	 * ��������Ψһ�ұ����ж�����
	 */
	public static synchronized void getMoney(){
		while(true){
			if(balance>0){
				System.out.println(Thread.currentThread().getName()+"ȡ����1000�飬��ʣ��"+(balance-1000)+"Ԫ");
				balance -= 1000;
			}else{
				System.out.println("��ȡ����");
				break;
			}
		}
	}
}