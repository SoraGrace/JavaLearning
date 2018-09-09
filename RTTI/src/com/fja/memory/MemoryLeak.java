package com.fja.memory;

import java.util.Arrays;

/**
 * 内存泄露：
 *  需求：
 *  使用数组模拟堆栈的存储方式
 *  
 *  堆栈的存储特点是先进后出，后进先出
 * 	
 * 【注意】
 * 	不再使用的对象，应该不要让变量指向该对象，否则gc不能回收该对象，容易内存泄漏
 */
public class MemoryLeak {

	public static void main(String[] args) {
		StackList list = new StackList();
		list.add("铁蛋");
		list.add("铁柱");
		list.add("狗娃");
		list.add("狗剩");
		System.out.println("元素个数： "+list.size());
		int len = list.size();
		for(int i=0;i<len;i++){
			System.out.println(list._pop());
		}
	}
}

class StackList{
	
	Object[] elements;
	
	int index = 0;
	
	public StackList() {
		super();
		this.elements = new Object[3];
	}
	
	//添加内容
	public void add(Object o){
		//添加元素之前先检查容量是否够用
		ensureCapcity();
		elements[index++] = o;
	}
	
	//检查当前的数组是否够用
	public void ensureCapcity(){
		if(index==elements.length){
			//计算新长度
			int len = elements.length + (elements.length >> 1);
			this.elements = Arrays.copyOf(elements, len);
		}
	}
	
	//出栈
	public Object pop(){
		//存在内存泄露的问题，出栈之后并没有把内存地址释放
		return elements[--index];
	}
	
	//对比上面的出栈方法，_pop()方法不会造成内存泄漏
	public Object _pop(){
		Object elementData = elements[--index];
		elements[index]=null;	//让gc把该对象回收
		return elementData;
	}
	
	//获取元素当前个数
	public int size(){
		return index;
	}
}
