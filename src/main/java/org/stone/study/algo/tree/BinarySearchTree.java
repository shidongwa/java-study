package org.stone.study.algo.tree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {
    private TreeNode root;

    public TreeNode find(int data) {
        TreeNode p = root;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }

        return null;
    }

    public BinarySearchTree() {
        root = new TreeNode(8);
        root.left = new TreeNode(3, new TreeNode(1), new TreeNode(4));
        root.right = new TreeNode(15, new TreeNode(11), new TreeNode(17));
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        TreeNode n = tree.find(15);

        System.out.println("n:" + n.data);
    }
}
