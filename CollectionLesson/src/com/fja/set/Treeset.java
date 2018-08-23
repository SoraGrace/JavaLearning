package com.fja.set;

import java.util.Comparator;
import java.util.TreeSet;

/**
 *  --------|Collection
 *  ----------------|Set 存取无序，元素不能重复
 *  ---------------------|HashSet 底层使用哈希表实现，特点是存取速度快
 *  ---------------------|TreeSet 可以使用元素的自然顺序进行排序，如果元素存在自然顺序，就会排序存储
 *  
 *  TreeSet的存储规则：左小右大
 *  
 */
public class Treeset {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TreeSet rs = new TreeSet();
		rs.add(2);
		rs.add(3);
		rs.add(1);
		System.out.println(rs);//[1, 2, 3],已经按照自然顺序排序、
		
		rs.clear();
		rs.add(new Employee(1,"狗蛋",50000));
		rs.add(new Employee(3,"铁柱",10000));
		rs.add(new Employee(2,"狗剩",14000));
		rs.add(new Employee(5,"铁蛋",23000));
		/**
		 *  使用TreeSet的注意事项：
		 *  	1.往TreeSet添加元素的时候，如果元素本身具备了自然顺序的特性，则会按照元素自然顺序的特性进行排序
		 *      2.往TreeSet添加元素的时候，如果元素本身不具备自然顺序的特性，那么元素必须要实现Comparable接口，
		 *        把比较规则定义在compareTo(T o)方法中。
		 *      3.【注意】 如果compareTo方法返回的是0，那么该元素会被视为重复元素，已经存在与集合中，不允许添加
		 * 		4.往TreeSet添加元素的时候，如果元素本身不具备自然顺序的特性，而且元素所属的类也没有实现comparable接口
		 * 		      那么在创建TreeSet的时候，必须要传入一个比较器(comparator)
		 *      5.【注意】如果一个类实现了 Comparable，且TreeSet初始化时也传入了一个Comparator,也就是同时存在两种比较规则，
		 *         这时候以Comparator的比较规则为准。
		 *      6.对于实现Comparable或者传入一个Comparator，推荐使用Comparator,复用性更强，不局限于一个类。
		 *      7.String类已经实现了Comparable接口，因此TreeSet可以对字符串排序。排序规则是，比较同位上的字符大小，如果同位的都相同，则比较字符串长度
		 */
		System.out.println(rs);//没有指定排序规则，则报错
		
		//对没有实现comparable接口的自定义元素排序,传入一个比较器
		TreeSet tree = new TreeSet(new MyComparator()); 
		tree.add(new Company(1001,"Alibaba"));
		tree.add(new Company(1003,"Tencent"));
		tree.add(new Company(1002,"Baidu"));
		System.out.println(tree);
			
		//比较字符串
		rs.clear();
		rs.add("abccccc");
		rs.add("b");
		rs.add("abc");
		System.out.println(rs);//[abc, abccccc, b]
		//abc和abccccc同位字母的都相同，比较长度。b和abccccc比较第一位b>a，因此b比abccccc大
		
		
		
		/**
		 * TreeSet的储存原理：底层使用了红黑树（二叉树）实现。
		 * 储存规则：左小右大
		 * 一个元素被添加到TreeSet中，分为四种情况：
		 *   1.集合中没有任何元素，将这个元素添加位根节点 
		 *   2.集合中有根节点，首先和根节点进行比较，比根节点小，则移到根节点的左侧，如果根节点的左侧没有元素，将该元素添加到左侧。
		 *     如果根节点的左侧已经存在着元素，则于已存在的元素进行比较，比已存在的元素小则至于左侧，大则右侧，如果还有元素则重复上述步骤
		 *   3.集合中有根节点，首先和根节点进行比较，比根节点大，则移到根节点的右侧，如果根节点的右侧没有元素，将该元素添加到右侧。
		 *     如果根节点的右侧已经存在着元素，则于已存在的元素进行比较，比已存在的元素小则至于左侧，大则右侧，如果还有元素则重复上述步骤
		 *   4.【注意】：如果连续添加的三个元素，没有构成二叉树，则会自动更换节点。比如：添加的是100,200,300。三个元素形成了一条直线，
		 *     100的右节点是200,200的右侧节点是300，并没有形成二叉树。这时会自动调整根节点，将原本的节点100改为200，100变为200的左侧节点，
		 *     300变成200的右侧节点，这样就重新形成了一个二叉树的结构
		 */
	}
}

/**
 * 实现 Comparable接口，TreeSet才知道如何排序
 */
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

class Company{
	int id;
	String name;
	public Company(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "{公司ID："+this.id+" 公司名称： "+this.name+"}";
	}
}


/**
 * 自定义比较器的compare方法中写的就是比较规则 
 */
class MyComparator implements Comparator{

	@Override
	public int compare(Object arg0, Object arg1) {
		Company c0 = (Company)arg0;
		Company c1 = (Company)arg1;
		return c0.id-c1.id;
	}
}