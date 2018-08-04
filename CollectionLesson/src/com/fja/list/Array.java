package com.fja.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * ���飺�洢ͬһ���������͵ļ������� 
 * 
 * ������ص㣺
 * 1). ֻ�ܴ洢ͬһ���������͵�����
 * 2). һ����ʼ�������ȹ̶�
 * 3). �����е�Ԫ��֮����ڴ��ַ�������ġ���Ҫ��
 * 
 * ע�⣺Object���͵�������Դ洢�������͵����ݡ� 
 * 
 * Arrays.toStirng():������������
 *  
 * ���϶Ա���������ƣ�
 * 	1). ���Ͽ��Դ洢�������͵Ķ������ݣ�����(����Object[]ֻ�ܴ洢һ�����͵�����) 
 *  2). ���ϵĳ��Ȼᷢ���仯������ĳ����ǹ̶��ġ�
 *  
 *  
 * -------| Collection  �ǡ��������ϡ��ĸ��ӿڡ�JDK���ṩCollection�ӿڵ�ֱ��ʵ�֣���Ϊ���Ϸ�Ϊ�����������ࡣ
 * 	  -------| List	���ʵ����List�ӿڵļ����ࣺ���򣬿��ظ�
 * 	  -------| Set  ���ʵ����Set�ӿڵļ����ࣺ���򣬲����ظ�
 * 
 * Collection����Ĺ��з�����
 * 	����: add()      				��ӳɹ�����true��ʧ�ܷ���false   
 * 		 addAll(Collection c)		��һ�����ϵ�Ԫ����ӵ�����һ�����ϵ���ȥ
 * 
 *  ɾ��: clear() 					��ռ����е�Ԫ��  
 *  	 remove()   			 	ָ�������е�Ԫ�ؽ���ɾ����ɾ���ɹ�����true��ʧ�ܷ���false
 *  	 removeAll(Collection c)    ɾ�����ü������봫�뼯�Ͻ�����Ԫ�� 		
 *       retainAll(Collection c)	�������ü������봫�뼯�ϵĽ���Ԫ�أ�������ɾ��
 *  
 *  �ж�: contains()   
 *       constainsAll()   
 *       isEmpty() 
 *  
 *  �鿴: size()
 *  
 *  ����: iterator()    
 *  	 toArray()
 *  
 */
public class Array {
	public static void main(String[] args) {
		Object[] arr = new Object[5];
		arr[0] = 0;
		arr[1] = 1.1;
		arr[2] = 10L;
		arr[3] = 't';
		arr[4] = "String";
		System.out.println(Arrays.toString(arr));
		//add()
		Collection<Object> c0 = new ArrayList<Object>();
		boolean flag = c0.add("abc");
		c0.add(1);
		c0.add(3.14);
		System.out.println("�Ƿ���ӳɹ��� "+flag);
		System.out.println(c0);
		
		//addAll()
		Collection<Object> c1 = new ArrayList<Object>();
		c1.add("def");
		c1.add("ghi");
		c1.addAll(c0);
		System.out.println(c1);
		
		//clear()
		c1.clear();
		System.out.println(c1);
		
		//remove()
		//boolean removeFlag = c0.remove("abc");
		boolean removeFlag = c0.remove("ab");		//ɾ�������ڵ�Ԫ�ؾͷ���false
		System.out.println(c0);
		System.out.println("�Ƿ�ɾ���ɹ���"+removeFlag);
		
		//removeAll()
		c1.add("abc");
		c0.add(new String("����"));
		c1.add(new String("����"));
		/**
		 *  �����������϶���"abc" �� �����ַ���������Ҫע�⣬�����ַ�����arraylist��contains()�������жϵ�ʱ���õ���String��equals()����
		 *  �����Ȼ��new String()���ַ�������Ȼ����c0��c1�������ϵĽ���
		 */
		c0.removeAll(c1);				
		System.out.println(c0);		
	}
}
