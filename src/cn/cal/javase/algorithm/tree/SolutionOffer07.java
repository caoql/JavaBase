package cn.cal.javase.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

public class SolutionOffer07 {

    public static void main(String[] args) {
        SolutionOffer07 solutionOffer07 = new SolutionOffer07();
        int[] preorder = {9, 3, 6, 7, 5, 4, 8};
        int[] inorder = {6, 3, 7, 9, 4, 5, 8};
        TreeNode head = solutionOffer07.buildTree(preorder, inorder);
        preorder(head);
        System.out.println();
        inorder(head);
    }

    private static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + ", ");
        preorder(root.left);
        preorder(root.right);
    }

    private static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        preorder(root.left);
        System.out.print(root.val + ", ");
        preorder(root.right);
    }

    private Map<Integer, Integer> inorderIndexCache = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // param check
        if (preorder == null || inorder == null || preorder.length != inorder.length || preorder.length == 0) {
            // 或者抛异常
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexCache.put(inorder[i], i);
        }

        return buildTree(preorder, 0, 0, preorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int rootIndex, int inleft, int inright) {
        // 递归终止条件
        if (rootIndex >= preorder.length || inleft > inright) {
            return null;
        }
        // 前序遍历为根节点在前
        int rootVal = preorder[rootIndex];
        // 获取根节点在中序遍历中的位置
        int inIndex = inorderIndexCache.get(rootVal);
        // 构造根节点
        TreeNode root = new TreeNode(rootVal);
        // 左子树递归
        root.left = buildTree(preorder, rootIndex + 1, inleft, inIndex - 1);
        // 右子树递归 - 左子树长度 + 根节点的位置 + 1
        root.right = buildTree(preorder, inIndex - inleft + rootIndex + 1, inIndex + 1, inright);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
