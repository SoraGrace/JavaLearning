package com.fja.practice.rebuild;

public class Test {
	public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};
//		char[] pre = {'G','D','A','F','E','M','H','Z'};
//		char[] in = {'A','D','E','F','G','H','M','Z'};
//		char[] pre = {'A','B','C','D','E','G','F'};
//		char[] in = {'C','B','E','G','D','F','A'};
		new RebuildBinaryTree().Solution(pre, in);
	}
}