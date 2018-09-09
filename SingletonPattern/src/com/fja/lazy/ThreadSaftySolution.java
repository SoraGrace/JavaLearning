package com.fja.lazy;

public class ThreadSaftySolution {
	
	public static void main(String[] args) {

	}
}

class _Single{
	private _Single(){};
	
	private static _Single s;
	//解决的第一种方式：synchronized()代码块，但是每次都要判断锁的状态，因此有效率问题
	public static _Single getInstance(){
		synchronized(_Single.class){
			if(s==null){
				s = new _Single();
			}
		}
		return s;
	}
	//第二种方式：增加一层判断，这样只有对象还没有创建前需要判断锁状态，之后当s!=null之后就不需要每次都去判断锁状态了。解决的效率问题，同时也保证了线程安全
	public static _Single _getInstance(){
		if(s==null){
			synchronized(_Single.class){
				if(s==null){
					s = new _Single();
				}
			}
		}
		return s;
	}
	
	//第三种解决方式：内部类
	
}