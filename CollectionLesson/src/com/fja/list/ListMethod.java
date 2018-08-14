package com.fja.list;

import java.util.ArrayList;
import java.util.List;

/**
 * list独有的方法：
 * 添加：
 * 		add(int index, E element)
 * 		addAll(int index, Collection<? extends E> c)
 * 
 * 获取：
 *      get(int index)
 *      indexOf(Object o)
 * 	    lastIndexOf(Object o)
 * 		subList(int fromIndex, int toIndex)
 * 
 * 修改：
 *      set(int index, E element)
 * 
 * 迭代：
 * 		listIterator()
 */
public class ListMethod {

	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		
		//add(int index, E element)将元素添加到集合中制定索引值的位置上
		list.add(3,"赵六");
		System.out.println(list);//[张三, 李四, 王五, 赵六]
		
		//addAll(int index, Collection<? extends E> c)把一个集合中所有的元素添加到调用方法集合的指定位置上
		List _list = new ArrayList();
		_list.add("狗剩");
		_list.add("铁蛋");
		list.addAll(3, _list);
		System.out.println(list);//[张三, 李四, 王五, 狗剩, 铁蛋, 赵六]
	}
	
}
