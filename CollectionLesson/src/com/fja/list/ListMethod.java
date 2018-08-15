package com.fja.list;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

	@SuppressWarnings("unchecked")
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
		
		//size()获取list的个数，get(index)获取元素,可以用来遍历list集合
		for(int i = 0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		
		//indexOf返回元素【第一次】出现的索引，不在则返回-1
		list.add("狗剩");
		System.out.println(list.indexOf("狗剩"));	//3
		System.out.println(list.indexOf("铁柱"));	//-1
		
		//lastIndexOf返回元素【最后一次】出现的索引
		System.out.println(list.lastIndexOf("狗剩"));//6
		
		//subList()指定开始和结束的索引值截取集合中的元素，返回一个list
		List sublist = list.subList(1, 3);
		//[李四, 王五]注意java中的开始和结束索引都是包头不包尾
		System.out.println(sublist);
		
		
		//set()使用指定的元素替换制定索引值的元素
		list.set(4,"铁柱");		//把铁蛋替换成铁柱
		System.out.println(list);
		
		
		/**
		 * ListIterator()返回一个迭代器，相比Iterator接口有一些特有的方法 
	     * hasPrevious() 有没有上一个元素
		 * previous()	 获取上一个元素，【注意】next()是先取出元素游标再向下移一位，previous()是游标先上移一位再取出元素
		 * add()		将元素插入当前指针只想的位置
		 * set()		替换迭代器最后一次返回的元素
		 */
		ListIterator lt = list.listIterator();
		//倒序遍历
		while(lt.hasNext()){
			lt.next();
		}
		while(lt.hasPrevious()){
			System.out.println(lt.previous());
		}
		
		//遍历时使用迭代器添加元素
		while(lt.hasNext()){
			System.out.print(lt.next()+" ");
			//使用add()添加元素时迭代器中的游标会下移一位，因此在遍历时添加的元素不会被next()迭代出来，但是已经被添加到了集合中
			lt.add("川普");
		}
		
		//【注意】在迭代器迭代元素的时候不允许使用【list对象的add()和remove()】添加删除元素，只能使用迭代器自身的add和remove方法
		while(lt.hasPrevious()){
			System.out.println(lt.previous());
			list.add("奥巴马");//直接报错，ConcurrentModificationException
		}
		//这样也是不行的，直接报错，ConcurrentModificationException
		list.add("奥巴马");
		lt.next();
		
		//这是可以的
		lt.next();
		list.add("奥巴马");
		//总结：也就是说在只用list添加元素后，不能使用迭代器的迭代代码

	}
	
}
