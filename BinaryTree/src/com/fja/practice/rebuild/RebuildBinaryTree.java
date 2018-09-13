package com.fja.practice.rebuild;
/**
 * 通过前序遍历和中序遍历重新构建二叉树 
 */
public class RebuildBinaryTree {
	
	public TreeNode Solution(int[] pre,int[] in){
		TreeNode binaryTree = rebuild(pre,in);
		return binaryTree;
	}
	
	public TreeNode rebuild(int[] pre,int[] in){
		
		//找到根节点在中序遍历中的索引
		int rootIndex = 0;
		for(int i = 0; i<in.length;i++){
			if(pre[0] == in[i]){
				rootIndex = i;
				break;
			}
		}
		
		//生成根节点
		TreeNode root = new TreeNode(pre[0]);
		
		//中序遍历中根节点大于零说明左边的子树有节点，开始递归
		if(rootIndex>0){
			//生成左子树的前序数组
			int[] leftPre = new int[rootIndex];	
			//生成左子树的中序数组
			int[] leftIn = new int[rootIndex];
			for(int i = 0; i < rootIndex; i++){
				//左子树一共是有rootIndex个元素，前序数组中索引是1到rootIndex，中序数组中是0到rootIndex-1
				leftPre[i] = pre[i+1];
				leftIn[i] = in[i];
			}
			root.left = rebuild(leftPre,leftIn);
		}
		
		//中序数组长度减去根节点中序索引大于一，说明有右子树，开始递归
		if(in.length - rootIndex > 1){
			//右子树的长度
			int len = in.length - rootIndex - 1;
			//生成右子树的前序数组
			int[] rightPre = new int[len];
			//生成右子树的中序数组
			int[] rightIn = new int[len];
			//左子树一共是有in.length - rootIndex - 1个元素
			for(int i = rootIndex+1; i < in.length; i++){
				//元素在前序数组和中序数组中索引是rootIndex+1到in.length-1
				rightPre[i-rootIndex-1] = pre[i];
				rightIn[i-rootIndex-1] = in[i];
			}
			root.right = rebuild(rightPre, rightIn);
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