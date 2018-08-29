package com.fja.practice;
/**
 * 使用装饰者模式完成如下需求：
 * 儿子，妈妈，爸爸三人都会工作，儿子会画画，妈妈会在画上涂颜料，爸爸会在涂完颜料的画上上相框 
 */
public class HomeWork {
	public static void main(String[] args) {
		Person p = new Father(new Mother(new Son()));
		p.work();
	}
}

interface Person{
	abstract void work();
}


class Son implements Person{
	@Override
	public void work() {
		System.out.println("画画");
	}
}

class Mother implements Person{
	Person p;
	public Mother(Person p) {
		super();
		this.p = p;
	}
	@Override
	public void work() {
		p.work();
		System.out.println("上颜色");
	}
}

class Father implements Person{
	Person p;
	public Father(Person p) {
		super();
		this.p = p;
	}
	@Override
	public void work() {
		p.work();
		System.out.println("上画框");
	}
}