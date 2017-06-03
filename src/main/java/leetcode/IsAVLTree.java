package leetcode;

import alg.TreeNode;

/**
 * Created by i325622 on 5/30/17.
 */
public class IsAVLTree {

    public boolean solution(TreeNode node) {
        if(node == null) {
            return true;
        }

        if(node.left == null && node.right == null) {
            return true;
        }

        int leftDepth = getTreeDepth(node.left);
        int rightDepth = getTreeDepth(node.right);

        if(Math.abs(leftDepth-rightDepth) > 1) {
            return false;
        }
        return solution(node.left) && solution(node.right);
    }

    int getTreeDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        if(node.left == null && node.right == null) {
            return 1;
        }

        int left = getTreeDepth(node.left);
        int right = getTreeDepth(node.right);

        return left > right ? left+1: right+1;
    }
}
