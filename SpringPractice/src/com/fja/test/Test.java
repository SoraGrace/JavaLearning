package com.fja.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fja.test.model.Array;
import com.fja.test.model.Cat;
import com.fja.test.model.Dog;
import com.fja.test.model.ICar;
import com.fja.test.model.Zoo;

public class Test {
	public static void main(String[] args){
		ApplicationContext ac = new ClassPathXmlApplicationContext("com/fja/test/config/bean.xml");
		Cat myCat = (Cat)ac.getBean("cat");
		Dog myDog = (Dog)ac.getBean("mypet");
		Dog dutch = (Dog)ac.getBean("dutch");
		
		Array arr = (Array)ac.getBean("arrTest");
		Array brr = (Array)ac.getBean("brrTest");
		Array crr = (Array)ac.getBean("crrTest");
		
		Zoo zoo = (Zoo)ac.getBean("zoo");
		Zoo _zoo = (Zoo)ac.getBean("anotherZoo");
		Zoo $zoo = (Zoo)ac.getBean("thirdZoo");			//通过p命名空间注入
		Dog garfield = (Dog)ac.getBean("Garfield");
		
		System.out.println("品种="+zoo.getD().getCategory());
		
		System.out.println("名称="+garfield.getName());
		System.out.println("种类="+garfield.getCategory());
		System.out.println("年龄="+garfield.getAge());
		System.out.println("颜色="+garfield.getColor());
		
		
		for(String str:crr.getArr()){
			System.out.println(str);
		}
		
		ICar benzProxy = (ICar)ac.getBean("carFactory");
		benzProxy.drive();
		
		ICar bmwProxy = (ICar)ac.getBean("carProxy");
		bmwProxy.drive();
	}
}
