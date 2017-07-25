package leetcode;

import alg.TreeNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by i325622 on 5/24/17.
 */
public class BinaryTreeLevelOrderTraversal {


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList();

        if(root == null) {
            return list;
        }

        Queue<TreeNode> queue = new LinkedBlockingDeque<>();
        queue.add(root);
        int nextLevel = 1;
        int currentLevel = 0;
        int zigzag = 0;

        List<Integer> levelList = new ArrayList<>();

        while(!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            levelList.add(treeNode.value);
            nextLevel--;

            if(treeNode.left != null) {
                queue.offer(treeNode.left);
                currentLevel++;
            }

            if(treeNode.right != null) {
                queue.offer(treeNode.right);
                currentLevel++;
            }

            if(nextLevel == 0) {
                nextLevel = currentLevel;
                currentLevel = 0;
                if(zigzag % 2 == 0) {
                    list.add(levelList);
                } else {
                    Collections.reverse(levelList);
                    list.add(levelList);
                }
                zigzag++;
                levelList = new ArrayList<>();
            }
        }

        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        if(root == null) {
            return list;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);
        int currentLevelCount = 1;
        int nextLevelCount = 0;
        int zigzag = 0;
        List<Integer> levelList = new ArrayList<>();

        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            TreeNode node;

            if(zigzag % 2 == 0) {
                node = stack1.pop();
                if(node.left != null) {
                    stack2.push(node.left);
                    nextLevelCount++;
                }
                if(node.right != null) {
                    stack2.push(node.right);
                    nextLevelCount++;
                }
            } else {
                node = stack2.pop();
                if(node.right != null) {
                    stack1.push(node.right);
                    nextLevelCount++;
                }
                if(node.left != null) {
                    stack1.push(node.left);
                    nextLevelCount++;
                }
            }
            currentLevelCount --;
            levelList.add(node.value);

            if(currentLevelCount == 0) {
                currentLevelCount = nextLevelCount;
                nextLevelCount = 0;
                list.add(levelList);
                levelList = new ArrayList<>();
                zigzag++;
            }

        }

        return list;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
//        node2.right = node7;
//        node3.left = node4;
        node3.right = node5;

        List<List<Integer>> list = binaryTreeLevelOrderTraversal.levelOrder2(node1);

        list.forEach(l -> {
            l.forEach( n->{
                System.out.print(n+ "  ");
            });
            System.out.println();
        });
    }

}
