package com.fja.practice.rebuild;

public class Test {
	public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};
		new RebuildBinaryTree().Solution(pre, in);
	}
}
