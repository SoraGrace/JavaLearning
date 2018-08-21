package com.fja.map;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * 双列集合：
 * -----|Map
 *   -----|TreeMap 是基于红黑树数据结构实现的，特点是会对元素的【键】进行排序
 * 
 * 注意事项
 * 	1.往TreeMap中添加元素的时候，如果元素的键具备自然的顺序，那么就会按照键的自然顺序特性进行排序存储。
 *  2.往TreeMap中添加元素的时候，如果元素的键不具备自然的顺序，那么键所属的类必须要实现Comparable接口
 *  3.往TreeMap中添加元素的时候，如果元素的键不具备自然的顺序，键所属的类也没有实现Comparable接口，那么
 *    就必须在创建TreeMap对象的时候传入比较器
 */
public class Tree {
	public static void main(String[] args) {
		TreeMap<Character, Integer> tr = new TreeMap<Character, Integer>();
		tr.put('a',8);
		tr.put('b',5);
		tr.put('e',7);
		tr.put('z',1);
		System.out.println(tr);		//{a=8, b=5, e=7, z=1}
		
		TreeMap<Emp, String> t = new TreeMap<Emp, String>();
		t.put(new Emp("狗娃",100),"007");
		t.put(new Emp("福贵",700),"001");
		t.put(new Emp("铁蛋",600),"006");
		t.put(new Emp("狗剩",600),"008");
		/**
		 * 问：键 new Emp("铁蛋",600)和new Emp("狗剩",600)是否重复？如果重复集合中留下的是哪一个，其对应的值又是多少
		 * 答：按照compareTo的规则，视为重复键，留下的是new Emp("铁蛋",600)，因为键重复不会被替换。值对应的是008，因为值相同会覆盖
		 */
		System.out.println(t);//{[姓名： 狗娃    薪水： 100]=007, [姓名： 铁蛋    薪水： 600]=008, [姓名： 福贵    薪水： 700]=001}
	}
}

class Emp implements Comparable<Emp>{
	String name;
	int salary;
	
	public Emp(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}

	@Override
	public int compareTo(Emp arg0) {
		return this.salary - arg0.salary;
	}

	@Override
	public String toString() {
		return "[姓名： "+this.name+"    薪水： "+this.salary+"]";
	}
}

class MyComparator implements Comparator<Emp>{

	@Override
	public int compare(Emp arg0, Emp arg1) {
		return arg0.salary - arg1.salary;
	}
	
}