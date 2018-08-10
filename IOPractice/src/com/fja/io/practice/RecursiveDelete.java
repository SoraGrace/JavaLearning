package com.fja.io.practice;

import java.io.File;

public class RecursiveDelete {
	
	public static void main(String[] args) {
		//循环删除包含多级目录的文件夹
		String path = "E:\\a";
		File dir = new File(path);
		recursiveDelete(dir);
	}
	
	static void recursiveDelete(File dir){
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File f:files){
				if(f.isFile()){
					//是文件，删除
					f.delete();
				}else if(f.isDirectory()){
					//是目录，递归
					recursiveDelete(f);
				}
			}
			dir.delete();
		}
	}
}
