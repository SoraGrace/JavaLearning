package com.fja.thread;
/**
 * �����̰߳�ȫ�����������
 * 1). �ж���̶߳���
 * 2). �߳�֮�乲��һ����Դ
 * 3). ���񷽷����ж���������������Դ
 * 
 * 
 * ����ķ�����
 * 1). ͬ�������
 * 	   synchronized(��Ҫ�����Ķ���){����}
 * 	       ע�⣺
 * 		���������������Ķ���(ֻҪ�Ƕ���Ϳ���)������ע���������������Ǳ��̹߳���Ψһ��
 * 		��ͬ��������е�����sleep()���������ͷ�������
 * 		��Ӱ�����ִ�е�Ч�ʣ�����������̰߳�ȫ���⣬��Ҫʹ��ͬ��
 * 
 * 2). ͬ������
 * 	        ������synchronized����һ������
 * 	        ͬ��������Ҫע������
 * 	   1).�����һ���Ǿ�̬��ͬ��������������������this������Ǿ�̬��ͬ���������������ǵ�ǰ��������������ֽ����ļ�(class����)
 * 	   2).ͬ���������������ǹ̶��ģ��������û�ָ����
 * 
 * Ϊʲô����Ķ��󶼿�����Ϊ������
 * ��Ϊ����Ķ����ڲ���ά����һ��״̬(�Ǿ�̬��Ա����)��javaͬ�����ƾ���ʹ���˶����е�״̬��Ϊ���ı�ʶ��
 * 
 */
public class ThreadSafty {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Seller s0 = new Seller("��Ʊ����1");
		Seller s1 = new Seller("��Ʊ����2");
		Seller s2 = new Seller("��Ʊ����3");
		s0.start();
		s1.start();
		s2.start();
	}

}

class Seller extends Thread{
	static int ticket_num = 50;
	static Object o = new Object(); //����ı��߳�������Ķ��󶼿��Գ�Ϊ��
	public Seller(String name){
		super(name);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			/**
			 * �ʣ� synchronized("��"){}�Ƿ�����ͬ���������ã�
			 * �𣺿��ԣ���Ϊ"��"������ʽ���ַ����Ǳ����ڳ������еģ�����֮����Ψһ�ұ��߳��ǹ����
			 *    ��ˣ�������Ϊ��Ϊ�����󡣵���synchronized(new String("��")){}�����ǲ��еģ�
			 *    ��Ϊûִ��һ��run()������������һ���µ��ַ��������Ҳ����߳�������
			 */
			//synchronized(o){					//����ͬ��
			//synchronized("��"){				//����ͬ��
			synchronized(new String("��")){		//������ͬ��
				if(ticket_num>0){
					System.out.println(currentThread().getName()+"�۳�:"+ticket_num);
					ticket_num--;
				}else{
					System.out.println("��Ʊ������");
					break;
				}
			}
		}
	}
}