package com.fja.exception;
/**
 * ------|Throwable   						所有异常和错误的超类
 * -----------|Error  						jvm的错误
 * -----------|Exception  					异常
 * ----------------|RuntimeException		运行期异常
 */
public class ThrowableClass {
	public static void main(String[] args) {
		//构造方法
		Throwable t = new Throwable();
		Throwable _t = new Throwable("Throwable对象");
		
		//toString(),返回完整类名+创建Throwable对象时传入的字符串
		String info = t.toString();
		System.out.println(info);		//java.lang.Throwable
		
		String _info = _t.toString();
		System.out.println(_info);		//java.lang.Throwable: Throwable对象
		
		//getMessage(),返回创建Throwable对象时传入的字符串
		String msg = t.getMessage();
		System.out.println(msg);       //null
		
		String _msg = _t.getMessage();
		System.out.println(_msg);       //Throwable对象
		
		//printStackTrace()，打印异常的栈信息，一层层打印受影响的代码行数
		test();
	}
	public static void test(){
		Throwable t = new Throwable("未知异常");
		t.printStackTrace();
	}
}



