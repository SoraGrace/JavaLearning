package com.fja.stack;

import java.util.Stack;

public class SimulateQueue {
	//使用两个堆栈模拟队列
	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		
		queue.add(10);
		queue.add(1);
		queue.add(5);
		
		int len = queue.size();
		for(int i = 0; i < len; i++){
			System.out.println(queue.poll());
		}
	}
}

class MyQueue{
	//存储区
	Stack<Integer> stor = new Stack<Integer>();
	//缓冲区
	Stack<Integer> temper = new Stack<Integer>();
	
	public boolean add(Integer elem){
		//是否添加成功
		boolean flag = true;
		
		flag = stor.add(elem);
		
		return flag;
	}
	
	//返回队列的头元素
	public Integer poll(){
		temper.empty();
		
		for(int i = 0; i < stor.size(); i++){
			temper.add(stor.get(i));
		}
		
		return stor.pop();
	}
	
	//队列的元素
	public int size(){
		
		return stor.size();
	}
}