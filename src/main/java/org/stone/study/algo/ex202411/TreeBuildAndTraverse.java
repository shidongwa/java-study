package org.stone.study.algo.ex202411;

import java.util.*;

public class TreeBuildAndTraverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // CBEFDA CBAEDF
        String[] arr = scanner.nextLine().split(" ");
        char[] postOrder = arr[0].toCharArray();
        char[] inOrder = arr[1].toCharArray();

        // 构建中序索引映射,不用每次都遍历 inOrder 数组
        Map<Character, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }

        TreeNode root = buildTree(postOrder, 0, postOrder.length -1,  inOrder, 0,
                inOrder.length - 1,  inOrderMap);
        traverse(root);
    }

    /**
     * 从后序遍历和中序遍历构建二叉树
     * @param postOrder
     * @param postStart
     * @param postEnd
     * @param inOrder
     * @param inStart
     * @param inEnd
     * @param inOrderMap
     * @return
     */
    public static TreeNode buildTree(char[] postOrder, int postStart, int postEnd,
                                     char[] inOrder, int inStart, int inEnd, Map<Character, Integer> inOrderMap) {
        if (postOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        // 从后序遍历中确定根节点
        char rootVal = postOrder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        // 根节点在中序遍历中的位置
        int rootIndex = inOrderMap.get(rootVal);
        // 左子树的大小
        int leftSize = rootIndex - inStart;
        // 递归构建左子树和右子树
        root.setLeft(buildTree(postOrder, postStart, postStart + leftSize - 1, inOrder,
                inStart, inStart + leftSize - 1, inOrderMap));
        root.setRight(buildTree(postOrder, postStart + leftSize, postEnd - 1,  inOrder,
                rootIndex + 1, inEnd, inOrderMap));

        return root;
    }

    /**
     * 层序遍历二叉树
     * @param root
     */
    public static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.getVal() + " ");

            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }
            if(node.getRight() != null) {
                queue.offer(node.getRight());
            }
        }
    }
}


/**
 * 二叉树节点
 */
class TreeNode {
    private char val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(char val) {
        this.val = val;
    }

    public TreeNode(char val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public char getVal() {
        return val;
    }

    public void setVal(char val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
