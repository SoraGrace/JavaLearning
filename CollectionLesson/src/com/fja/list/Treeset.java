package com.fja.list;

import java.util.TreeSet;

/**
 *  --------|Collection
 *  ----------------|Set 存取无序，元素不能重复
 *  ---------------------|HashSet 底层使用哈希表实现，特点是存取速度快
 *  ---------------------|TreeSet 可以使用元素的自然顺序进行排序，如果元素存在自然顺序，就会排序存储
 */
public class Treeset {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TreeSet rs = new TreeSet();
		rs.add(2);
		rs.add(3);
		rs.add(1);
		System.out.println(rs);//[1, 2, 3],已经按照自然顺序排序、
		
		TreeSet tree = new TreeSet();
		tree.add(new Employee(1,"狗蛋",50000));
		tree.add(new Employee(3,"铁柱",10000));
		tree.add(new Employee(2,"狗剩",14000));
		tree.add(new Employee(5,"铁蛋",23000));
		/**
		 *  使用TreeSet的注意事项：
		 *  	1.往TreeSet添加元素的时候，如果元素本身具备了自然顺序的特性，则会按照元素自然顺序的特性进行排序
		 *      2.往TreeSet添加元素的时候，如果元素本身不具备自然顺序的特性，那么元素必须要实现Comparable接口，
		 *        把比较规则定义在compareTo(T o)方法中。
		 * 
		 */
		System.out.println(tree);//没有指定排序规则，则报错
	}
}


class Employee implements Comparable{
	int id;
	String name;
	double salary;
	
	public Employee(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "{ 编号： "+this.id+" 姓名："+this.name+" 薪水："+this.salary+"}";
	}

	@Override
	public int compareTo(Object o) {
		// 元素与元素的比较规则：负数表示<  0表示=  正数表示>
		return (int)(this.salary-((Employee)o).salary); 
	}	
}