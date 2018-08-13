package com.fja.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 数组：存储同一种数据类型的集合容器 
 * 
 * 数组的特点：
 * 1). 只能存储同一种数据类型的数据
 * 2). 一旦初始化，长度固定
 * 3). 数组中的元素之间的内存地址是连续的【重要】
 * 
 * 注意：Object类型的数组可以存储任意类型的数据。 
 * 
 * Arrays.toStirng():输出数组的内容
 *  
 * 集合对比数组的优势：
 * 	1). 集合可以存储任意类型的对象数据，数组(除了Object[]只能存储一种类型的数据) 
 *  2). 集合的长度会发生变化，数组的长度是固定的。
 *  
 *  
 * -------| Collection  是【单例集合】的根接口。JDK不提供Collection接口的直接实现，因为集合分为无序有序两类。
 * 	  -------| List	如果实现了List接口的集合类：有序，可重复
 * 	  -------| Set  如果实现了Set接口的集合类：无序，不可重复
 * 
 * Collection定义的共有方法：
 * 	增加: add()      				添加成功返回true，失败返回false   
 * 		 addAll(Collection c)		把一个集合的元素添加到另外一个集合当中去
 * 
 *  删除: clear() 					清空集合中的元素  
 *  	 remove()   			 	指定集合中的元素进行删除，删除成功返回true，失败返回false
 *  	 removeAll(Collection c)    删除调用集合中与传入集合交集的元素 		
 *       retainAll(Collection c)	保留调用集合中与传入集合的交集元素，其他的删除
 *  
 *  判断: contains()   
 *       constainsAll()   
 *       isEmpty() 
 *  
 *  查看: size()
 *  
 *  迭代: iterator()    
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
		System.out.println("是否添加成功： "+flag);
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
		boolean removeFlag = c0.remove("ab");		//删除不存在的元素就返回false
		System.out.println(c0);
		System.out.println("是否删除成功："+removeFlag);
		
		//removeAll()
		c1.add("abc");
		c0.add(new String("铁蛋"));
		c1.add(new String("铁蛋"));
		/**
		 *  这里两个集合都有"abc" 和 铁蛋字符串，这里要注意，对于字符串，arraylist的contains()方法在判断的时候用的是String的equals()方法
		 *  因此虽然是new String()的字符串，依然算是c0和c1两个集合的交集
		 */
		c0.removeAll(c1);				
		System.out.println(c0);
		
		//retianAll()保留调用集合中与传入集合的交集元素，调用集合中的其他元素删除
		c0.add("铁蛋");//c0--->[1, 3.14, 铁蛋]        c1--->[abc, 铁蛋]
		c0.retainAll(c1);
		System.out.println(c0);//[铁蛋]
		
		//isEmpty()    判断集合是否为空
		//c0--->[铁蛋]
		System.out.println("c0集合是否为空： "+c0.isEmpty());
		
		
		//contains() 判断集合中是否存在某一个元素
		/**
		 * 问：为什么89757不存在？ 
		 * 答：contains()方法的底层实现其实是调用传入参数的equals方法和集合内的元素进行比对，
		 * 	    在上述代码中，传入的对象new Person("89757","铁蛋")的内存地址和集合中的new Person("89757","铁蛋")不一样
		 *    因此集合判断不存在该元素
		 *    
		 * 问：如果需要集合判断，只要是idCode一样的对象就判断该对象存在于集合中，应该如何实现？
		 * 答：重写对象的equals方法
		 */
		c0.clear();
		c0.add(new Person("89757","铁蛋"));
		boolean existFlag = c0.contains(new Person("89757","铁蛋"));		
		System.out.println("idCode是89757的人是否存在： "+existFlag);
		
		
		//toArray()方法会将集合内的数据放入一个Object[]数组中
		Object[] o = c1.toArray();		//c1--->[abc, 铁蛋]
		for(int i = 0;i<o.length;i++){
			System.out.print((String)o[i]+",");			//类型需要强转
		}
	}
}


class Person{
	String idCode;
	String name;
	public Person(String idCode, String name) {
		super();
		this.idCode = idCode;
		this.name = name;
	}
	/**
	 * 重写Person类的equals方法，变为比较idCode的值，只要相等就返回true
	 */
	@Override
	public boolean equals(Object o) {			
		return ((Person)o).idCode == this.idCode;
	}
}