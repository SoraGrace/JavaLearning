package com.fja.practice.rebuild;
/**
 * 通过前序遍历和中序遍历重新构建二叉树 
 * 步骤：
 * 	通过pre[0]确定根节点
 *  通过在in[]中确定根节点划分出左右子树的元素
 */
public class RebuildBinaryTree {
	public TreeNode Solution(int[] pre,int[] in){
		if(pre.length!=in.length)return null;
		//根节点：
		TreeNode root = new TreeNode(pre[0]);
		
		root = rebuild(root,pre,in,-1,-1,-1);
		return root;
	}
	//右子树设最大值，左子树设最小值
	private TreeNode rebuild(TreeNode root,int[] pre,int[] in,int parent_in_index,int maxIndex,int minIndex){
		System.out.println("----"+root.val);
		int pre_index = 0;
		int in_index = 0;
		for(int i = 0;i<pre.length;i++){
			if(pre[i]==root.val){
				pre_index = i;
			}
			if(in[i]==root.val){
				in_index = i;
			}
		}
		int leftTreeLength = 0;
		int rightTreeLength = 0;
		//根节点
		if(parent_in_index==-1){
			leftTreeLength = in_index;
			rightTreeLength = in.length-1-in_index;
		}else{
			if(parent_in_index>in_index&&in_index>=0){					//左子树
				leftTreeLength = in_index-minIndex;							//节点有左子树
				rightTreeLength = maxIndex-in_index;
			}else if(in_index>0){									//右子树
				rightTreeLength = maxIndex-in_index;
				leftTreeLength = in_index-minIndex;								//节点有右子树
			}
		}
		
		
		if(leftTreeLength>0){
			int max = 0;
			int min = 0;
			if(parent_in_index>in_index||parent_in_index==-1){
				max = in_index-1;			//可以取到
			}else{
				max = in_index-1;
				min = parent_in_index+1;
			}
			if(pre_index+1>in.length-1){
				root.left = null;
			}else{
				int value = pre[pre_index+1];
				System.out.println(value);
				root.left = rebuild(new TreeNode(value),pre,in,in_index,max,min);
			}
		}else{
			root.left = null;
		}
		if(rightTreeLength>0){
			int max = 0;
			int min = 0;
			if(parent_in_index>in_index){
				min = in_index+1;
				max = parent_in_index-1;
			}else{
				min = in_index+1;
				max = in.length-1;
			}
			
			if(pre_index+leftTreeLength+1>in.length-1){
				root.right = null;
			}else{
				int value = pre[pre_index+leftTreeLength+1];
				System.out.println(value);
				root.right = rebuild(new TreeNode(value),pre,in,in_index,max,min);
			}
		}else{
			root.right = null;
		}
		return root;
	}
}


class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) {
		super();
		this.val = val;
	}	
}