package com.fja.practice.rebuild;
/**
 * 通过前序遍历和中序遍历重新构建二叉树 
 * 步骤：
 * 	通过pre[0]确定根节点
 *  通过在in[]中确定根节点划分出左右子树的元素
 */
public class RebuildBinaryTree {
	public TreeNode Solution(int[] pre,int[] in){
		//根节点：
		TreeNode root = new TreeNode(pre[0]);
		return rebuild(root,pre,in,in.length);
	}
	
	private TreeNode rebuild(TreeNode root,int[] pre,int[] in,int total){
		System.out.println(root.val);
		int root_pre_index = 0;
		int root_in_index = 0;
		for(int i = 0;i<pre.length;i++){
			if(pre[i]==root.val){
				root_pre_index = i;
			}
			if(in[i]==root.val){
				root_in_index = i;
			}
		}
		int leftTreeCount = root_in_index;
		int rightTreeCount = total-leftTreeCount-1;
		if(total>0&&total>leftTreeCount&&leftTreeCount>=0){
			root.left = rebuild(new TreeNode(pre[root_pre_index+1]),pre,in,leftTreeCount);
		}else{
			root.left = null;
		}
		
		if(total>0&&total>rightTreeCount&&rightTreeCount>0){
			root.right = rebuild(new TreeNode(pre[pre.length-rightTreeCount]),pre,in,rightTreeCount);
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