package leetcode;

import alg.TreeNode;

import java.util.*;

/**
 * Created by i325622 on 5/24/17.
 * <p>
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {


    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return compareLeftAndRight(root.left, root.right);
    }

    public boolean compareLeftAndRight(TreeNode left, TreeNode right) {
        boolean result = true;
        if(left != null && right != null && left.value == right.value) {
            result = result && compareLeftAndRight(left.left, right.right);
            result = result && compareLeftAndRight(left.right, right.left);
        } else if(left == null && right == null){
            result = true;
        } else {
            return false;
        }
        return result;
    }

    public static void main(String[] args) {
        SymmetricTree symmetricTree = new SymmetricTree();

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;

        boolean result = symmetricTree.isSymmetric(node1);
        System.out.println(result);
    }
}
