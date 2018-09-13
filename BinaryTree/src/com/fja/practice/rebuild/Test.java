package com.fja.practice.rebuild;

public class Test {
	public static void main(String[] args) {
		int[] pre = {1,2,4,7,3,5,6,8};
		int[] in = {4,7,2,1,5,3,8,6};

		TreeNode binaryTree = new RebuildBinaryTree().Solution(pre, in);
		preOrder(binaryTree);
		System.out.println();
		inOrder(binaryTree);
		System.out.println();
		postOrder(binaryTree);
	}
	
	//先根再左再右
	public static void preOrder(TreeNode binaryTree){
		if(binaryTree == null)return;
		System.out.print(binaryTree.val+" ");
		preOrder(binaryTree.left);
		preOrder(binaryTree.right);
	}
	
	//先左再根再右
	public static void inOrder(TreeNode binaryTree){
		if(binaryTree == null)return;
		inOrder(binaryTree.left);
		System.out.print(binaryTree.val+" ");
		inOrder(binaryTree.right);
	}
	
	//先左再右再根
	public static void postOrder(TreeNode binaryTree){
		if(binaryTree == null)return;
		postOrder(binaryTree.left);
		postOrder(binaryTree.right);
		System.out.print(binaryTree.val+" ");
	}
}