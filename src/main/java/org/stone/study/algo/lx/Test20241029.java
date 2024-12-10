package org.stone.study.algo.lx;

public class Test20241029 {
    public static void main(String[] args) {
        int n = 4;

        //TreeNode root = genTree(3);

        TreeNode<Integer> parent = new TreeNode<>(1);
        parent.setLeft(dfs2(parent, n - 1, 0));
        parent.setRight(dfs2(parent, n - 1, 1));
        System.out.println(parent);
    }

    public static TreeNode<Integer> dfs2(TreeNode<Integer> parent, int depth, int flag) {
        if(depth == 0) return null;

        TreeNode<Integer> curr = new TreeNode<>(flag == 0 ? parent.getData() * 2 : parent.getData() * 2 + 1);
        curr.setParent(parent);
        curr.setLeft(dfs2(curr, depth -1, 0));
        curr.setRight(dfs2(curr, depth -1, 1));

        return curr;
    }
}
