package com.fja.set;

import java.util.HashSet;

/**
 *  --------|Collection
 *  ----------------|Set 存取无序，元素不能重复
 *  ---------------------|HashSet 底层使用哈希表实现，特点是存取速度快
 */
public class Hashset {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/**
		 * HashSet的实现原理:
		 * 	往HashSet中添加元素时，会先调用元素的hashCode方法得到元素的哈希码值，
		 *  通过元素的哈希值的位移等运算，算出元素应该存储于哈希表的位置，然后尝试将元素放入该位置。
		 *  之后又两种情况：
		 *  	情况一： 
		 * 			如果该存储位置当前没有存放任何元素，那么就将该元素直接存储到该位置上
		 *      情况二：
		 *      	如果该存储位置上已经存有其他元素了，则会调用元素的equals方法于存有的元素进行比较
		 *      	如果equals返回true，则视两个元素相同，是重复元素，不与添加，如果equals方法返回
		 *      	false，则将该元素存储在该位置上。(哈希表是桶式结构，一个位置可以存放多个元素)
		 */
		
		//下面就利用HashSet的实现原理，自定义存储规则
		HashSet hs = new HashSet();
		hs.add(new _Person(1,"狗剩"));
		hs.add(new _Person(2,"铁蛋"));
		System.out.println("是否添加成功： "+hs.add(new _Person(1,"福贵")));// false
		System.out.println(hs);	//[{姓名： 狗剩}, {姓名： 铁蛋}]
		
		//因为我们重写了hashCode方法和equals方法，所以只要是id相同HashSet就会认为两个元素是重复元素
		
		//HashCode默认情况下表示的是内存地址
		String str = "狗剩";
		String _str = new String("狗剩");
		System.out.println(str==_str);						//false
		System.out.println(str.hashCode()==_str.hashCode());//true
		//上述情况，内存地址不同，hashCode相同，是因为String类重写了hashCode方法，只要两个字符串的内同一致，则hashCode方法返回值一致
	}

}

class _Person{
	int idCode;
	String name;
	public _Person(int idCode, String name) {
		super();
		this.idCode = idCode;
		this.name = name;
	}
	//重写hashCode方法
	@Override
	public int hashCode() {
		return this.idCode;
	}
	@Override
	public boolean equals(Object obj) {
		return this.idCode == ((_Person)obj).idCode;
	}
	@Override
	public String toString() {
		return "{姓名： "+this.name+"}";
	}
	
}