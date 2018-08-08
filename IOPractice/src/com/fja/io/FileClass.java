package com.fja.io;

import java.io.File;

/**
 * IO流(Input Output):
 * 主要的作用：设备与设备之间的数据传输。
 * 比如：1).将硬盘的数据读取到内存中
 * 	   2).将内存的数据写入硬盘中
 * 	   3).将键盘的数据读取到内存中。 
 * 
 * IO技术的应用场景：
 * 	   1).导出报表
 * 	   2).上传文件
 *     3).下载文件
 *     4).解析XML文件
 *     
 * 问：一般数据是以什么形式保存到硬盘上的
 * 答：一般是以文件的形式存在。sun使用了File类来描述文件或者文件夹
 * 
 * 【注意】
 * 代码中所演示的README.md有两个，一个是在E:根目录，另一个是FileClass类文件同级目录下
 */
public class FileClass {
	public static void main(String[] args) {
		/**
		 * @constructor
		 * 1). File(String pathname) 指定文件或者文件夹的路径
		 * 2). File(String parent,String child) 
		 * 3). File(File parent,String child)
		 */
		//直接填写路径
		File file = new File("src/com/fja/io/README.md");
		System.out.println("README.md是否存在："+file.exists());		//true
		
		//将路径拆分，试验第二种构造器
		File _file = new File("src/com/fja/io","README.md");
		
		//推荐使用第三种方式，因为父路径的对象可以先调用方法，相比于上面的方法更加的灵活
		File parent = new File("src/com/fja/io");
		File child = new File(parent,"README.md");
		
		//判断文件是否存在
		System.out.println("README.md是否存在："+child.exists());		//true
		
		//windows的目录分隔符用的是反斜杠(要写两个正斜杠)。【注意】这种目录分隔符的写法在linux系统中是不合法的
		_file = new File("E:\\README.md");
		System.out.println("E:\\README.md是否存在："+_file.exists());		//true
		
		//为了解决windows系统和linux、unix系统的目录分隔符的不统一问题，File提供了静态常量separator
		_file = new File("E:"+File.separator+"README.md");
		System.out.println("README.md是否存在："+_file.exists());		//true
		
		//ps.其实windows系统正斜杠(/)和反斜杠(\)都是支持的，相面的代码已经展示了两种目录分隔符
		
		//获取File的绝对路径：
		file = new File(".");
		System.out.println("当前文件的绝对路径： "+file.getAbsolutePath()); //D:\work\IOPractice\.
		
		//通过绝对路径推算相对路径，当前路径就是项目的文件夹。
		file = new File("src\\com\\fja\\io\\README.md");
		System.out.println("README.md是否存在："+file.exists());		//true
		
		//【注意】如果程序和资源不是在同一个盘符下的时候，是无法通过相对路径获取File对象的。
		file = new File("..\\..\\..\\E:\\README.md");
		System.out.println("README.md是否存在："+file.exists());		//false
	}
}
