package org.stone.study.algo.lx;

import java.util.LinkedList;

public class TestTree {

    public static void main(String[] args) {
        int n = 3;

        //TreeNode root = genTree(3);

//        TreeNode<Integer> root = new TreeNode<>(1);
        TreeNode<Character> root1 = dfs(4);
        TreeNode<Character> root2 = dfs(4);
        TreeNode<Character> root3 = dfs(4);
        root1.setParent(root2);
        root2.setParent(root3);
        root3.setParent(root1);
        //System.out.println(root);

        TreeNode<Character> start = root1;
        TreeNode<Character> end = root3;
        int steps = traverse(start, end);
        System.out.println(steps);
    }

    public static int traverse(TreeNode<Character> start, TreeNode<Character> end) {
        int step1 = (int)traverseChild(start, end);
        if(step1 != Integer.MAX_VALUE) {
            return step1;
        }
        int step2 = 1 + (int)traverseParent(start, start.getParent(), end);

        return step2;
    }

    public static long traverseChild(TreeNode<Character> start, TreeNode<Character> end) {
        if(start == end) {
            return 0;
        }

        if(start == null) return Integer.MAX_VALUE;

        long steps = Integer.MAX_VALUE;
        if(start.getLeft() != null) {
            steps = Math.min(steps, 1 + traverseChild(start.getLeft(), end));
        }

        if(steps != Integer.MAX_VALUE) {
            return steps;
        }

        if(start.getRight() != null) {
            steps = Math.min(steps, 1 + traverseChild(start.getRight(), end));
        }

        return steps;
    }

    public static long traverseParent(TreeNode<Character> pre, TreeNode<Character> cur, TreeNode<Character> end) {
        if(cur == end) {
            return 0;
        }

        long childSteps = Integer.MAX_VALUE;
        long parentSteps = Integer.MAX_VALUE;
        if(cur.getLeft() == pre) {
            childSteps = Math.min(childSteps, traverseChild(cur.getRight(), end) + 1);
        } else if(cur.getRight() == pre) {
            childSteps = Math.min(childSteps, traverseChild(cur.getLeft(), end) + 1);
        } else {
            parentSteps = traverseChild(cur.getLeft(), end) + 1;
            if(parentSteps != Integer.MAX_VALUE) {
                return parentSteps;
            }
            parentSteps = traverseChild(cur.getRight(), end) + 1;
        }

        if(childSteps != Integer.MAX_VALUE) {
            return childSteps;
        }

        if(parentSteps != Integer.MAX_VALUE) {
            return parentSteps;
        }

        parentSteps = traverseParent(cur, cur.getParent(), end) + 1;

        return parentSteps;
    }

    public static TreeNode<Character> dfs(int layer) {
        TreeNode<Character> root = new TreeNode<Character>('A');

        dfs2(root, layer);

        return root;
    }

    public static int getDigitFromChar(Character c) {
        return c - 'A' + 1;
    }

    public static Character getCharFromDigit(int number) {
        return (char)('A' + (number - 1) % 26);
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

    public static TreeNode genTree(int layer) {
        if (layer == 0) {
            return null;
        }
        if ( layer == 1) {
            return new TreeNode(null);
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        //TreeNode parent = null;
        TreeNode root = new TreeNode(null);
        queue.offer(root);
        int curLayer = 1;
        while(!queue.isEmpty() && curLayer < layer) {
            int size = queue.size();
            while(size > 0) {
                TreeNode cur = queue.poll();

                TreeNode left = new TreeNode(null);
                TreeNode right = new TreeNode(null);
                cur.setLeft(left);
                cur.setRight(right);

                queue.offer(left);
                queue.offer(right);

                --size;
            }

            ++curLayer;
        }

        dfs(root);
        return root;
    }

    public static <T> void dfs(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        TreeNode<T> left = root.getLeft();
        if(left != null) {
            left.setParent(root);
            dfs(left);
        }
        TreeNode<T> right = root.getRight();
        if(right != null) {
            right.setParent(root);
            dfs(right);
        }
    }
}
