package org.stone.study.algo.lx;

public class Test20241030 {

    public static void main(String[] args) {
        TreeNode<Character> root1 = genTree(3);

        TreeNode<Character> root2 = genTree(3);

        TreeNode<Character> root3 = genTree(3);

        root1.setParent(root2);
        root2.setParent(root3);
        root3.setParent(root1);

        traverse(root1.getLeft(), null);
    }

    public static TreeNode<Character> genTree(int layer) {
        if(layer == 0) {
            return null;
        }

        TreeNode<Character> root = new TreeNode<>('A');
        dfs2(root, 3);

        return root;
    }
    public static void dfs2(TreeNode<Character> root, int layer) {
        if(layer == 1) return;

        TreeNode<Character> left = new TreeNode<>(getCharFromDigit(getDigitFromChar(root.getData()) * 2), null, null, root);
        TreeNode<Character> right = new TreeNode<>(getCharFromDigit(getDigitFromChar(root.getData()) * 2 + 1), null, null, root);
        root.setLeft(left);
        root.setRight(right);
        dfs2(left, layer -1);
        dfs2(right, layer - 1);
    }

    public static Character getCharFromDigit(int i) {
        return (char)('A' + i - 1);
    }

    public static int getDigitFromChar(Character c) {
        return c - 'A' + 1;
    }

    public static void traverse(TreeNode<Character> node, TreeNode<Character> firstRoot) {

        //System.out.println(node.getData());

        traverseChild(node, firstRoot);

        traverseParent(node, node.getParent(), firstRoot);
    }

    public static void traverseChild(TreeNode<Character> node, TreeNode<Character> firstRoot) {
        if(node == null) {
            return;
        }

        if(firstRoot == null && node.getData() == 'A') {
            firstRoot = node;
        }

        System.out.println(node.getData());

        if(node.getLeft() != null) {
            traverseChild(node.getLeft(), firstRoot);
        }

        if(node.getRight() != null) {
            traverseChild(node.getRight(), firstRoot);
        }
    }

    public static void traverseParent(TreeNode<Character> preNode, TreeNode<Character> curNode, TreeNode<Character> firstRoot) {
        if(curNode == firstRoot) {
            return;
        }

        if(firstRoot == null && curNode.getData() == 'A') {
            firstRoot = curNode;
        }

        System.out.println(curNode.getData());


        if(curNode.getLeft() == preNode) { // 是从左节点往上的，需要往下遍历右节点
            traverseChild(curNode.getRight(), firstRoot);
        } else if(curNode.getRight() == preNode) { // 是从右节点往上的，需要往下遍历左节点
            traverseChild(curNode.getLeft(), firstRoot);
        } else { // 既不是左节点也不是右节点往上的，说明是从一个子树到另外一颗子树的
            traverseChild(curNode.getLeft(), firstRoot);
            traverseChild(curNode.getRight(), firstRoot);
        }

        // 继续往上遍历
        traverseParent(curNode, curNode.getParent(), firstRoot);
    }

}
