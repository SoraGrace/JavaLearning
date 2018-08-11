package com.fja.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

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
		
		
		
		
		//File类常用方法
		/**
		 * 重命名文件(renameTo) 【注意】如果renameTo的文件和目标文件是在同一级目录下面，则renameTo的作用等于重命名
		 * 如果renameTo的文件和目标文件不在同一级目录下，则renameTo的作用等同于剪切，且无法对于文件夹生效
		 */
		
		file = new File("E:\\README.md");
		File dest = new File("E:\\$README.md");
		file.renameTo(dest);
		
		//创建新的文件(createNewFile) 和创建新的文件夹(mkdir)
		File dir = new File("E:\\aa");		
		try {
			dir.mkdir();				//在E盘下创建一个aa文件夹
			if(dir.exists()){
				//在aa文件夹里面创建aa.txt
				File newfile = new File(dir.getAbsolutePath()+File.separator+"aa.txt");
				newfile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//列出目录下面的所有文件(listFiles)
		File[] files = dir.listFiles();
		for(File f:files){
			System.out.println(f.getName()); 		//aa.txt
		}
		
		
		
		/**
		 * 文件名过滤器(FilenameFilter)配合listFiles()方法使用，返回当前目录中符合过滤条件的文件或者子目录,对文件使用返回null
		 * 当传入过滤器之后listFiles()方法每获得一个文件就会调用accept()方法，当accept()方法返回true就将该文件放入返回的数组中
		 */
		files = dir.listFiles(new MyFileFilter());
		for(File f:files){
			System.out.println(f.getName()); 		//aa.txt
		}
		
		//返回目录下面所有文件和子目录的名称
		String[] names = dir.list();
		for(String name:names){
			System.out.println(name); 		//aa.txt
		}
		//list()方法也可以传入一个过滤器。也是重写过滤器的accept方法
		names = dir.list(new MyFileFilter());
		for(String name:names){
			System.out.println(name); 		//aa.txt
		}
	}
}

class MyFileFilter implements FilenameFilter{
	/**
	 * @param dir {File} 目标目录
	 * @param name {String} 文件名 
	 */
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".txt");			//将后缀是txt的文件返回
	}
}
