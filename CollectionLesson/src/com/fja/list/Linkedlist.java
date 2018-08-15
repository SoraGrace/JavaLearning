package com.fja.list;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * LinkedList的底层是用链表实现的
 * 增删快，查改慢
 * 
 * LinkedList的特有方法：
 * 	addFirst()
 *  addLast()  和add()方法效果一样
 *  getFirst()
 *  getLast()
 *  removeFirst()
 *  removeLast()
 *  
 *  push()
 *  pop()
 *  poll()
 *  offer()
 */
public class Linkedlist {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.add("狗娃");
		ll.add(1,"李四");
		System.out.println(ll);
		
		ll.addFirst("铁蛋");
		ll.addLast("张三");
		System.out.println(ll);
		
		System.out.println(ll.getFirst());
		System.out.println(ll.getLast());
		
		//删除首位元素并且返回
		String fistElements = (String)ll.removeFirst();
		System.out.println("移除的首位元素： "+fistElements);
		System.out.println(ll);
		
		//删除末位元素并且返回
		String lastElements = (String)ll.removeLast();
		System.out.println("移除的末位元素： "+lastElements);
		System.out.println(ll);
		
		//将元素插入集合的开头
		ll.push("赵六");
		System.out.println(ll);
		
		//移除并返回集合的第一个元素
		System.out.println("集合的首元素： "+ll.pop());
		
		//offer()将元素添加到末尾
		ll.offer("铁蛋");
		System.out.println(ll);
		
		//poll()移除并返回集合的第一个元素
		System.out.println("集合的首元素： "+ll.pop());
		
		//descendingIterator()返回倒序的迭代器
		System.out.println(ll);//[李四, 铁蛋]
		Iterator it= ll.descendingIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		/**
		 * push()和pop()方法是实现了Deque<E>接口 (模拟堆栈：先行后出)
		 * offer()和poll()方法是实现了Deque<E>接口 (模拟队列：先进先出)
		 */
		
	}
}

//模拟堆栈数据结构：
class StackList{
	LinkedList list;
	public StackList(){
		list = new LinkedList();
	}
	//进栈
	public void add(Object o){
		list.push(o);
	}
	//弹栈
	public Object pop(){
		return list.pop();
	}
	
	//获取元素个数
	public int size(){
		return list.size();
	}
}

//模拟队列数据结构:
class QueueList{
	LinkedList list;
	public QueueList(){
		list = new LinkedList();
	}
	//将元素添加到队列末尾
	public void add(Object o){
		list.offer(o);
	}
	
	public Object remove(){
		return list.poll();
	}
	
	public int size(){
		return list.size();
	}
}
