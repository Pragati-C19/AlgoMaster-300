import java.util.*;

import javax.swing.tree.TreeNode;

public class Codec {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
    }


    // Helper Function : To Print Binary Tree 
    public static List<String> printTreeAsArrayFormat(TreeNode root) {
        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
    
        queue.add(root);
    
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
    
            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.add(node.left);
                queue.add(node.right);
            }
        }
    
        // Trim trailing "null"s (Leetcode does this)
        int i = result.size() - 1;
        while (i >= 0 && result.get(i).equals("null")) {
            result.remove(i--);
        }
    
        return result;
    }    

    public static void main (String[] args){

        Codec ser = new Codec();
        Codec deser = new Codec();

        TreeNode root1 = {1, 2, 3, null, null, 4, 5};
        TreeNode ans1 = deser.deserialize(ser.serialize(root1))
        System.out.println("Output1 : " + printTreeAsArrayFormat(ans1) + "\n");

        TreeNode root2 = { };
        TreeNode ans2 = deser.deserialize(ser.serialize(root2))
        System.out.println("Output2 : " + printTreeAsArrayFormat(ans2) + "\n");

    }

}

/*
 * 
 * Intuitions :
 * 
 * 1. Serialization -> is the proccess of converting data structure or object
 * into a sequence of bits
 * 2. here we need to serialize and deserialize the binary tree
 * 3. convert that binary tree into String
 * 4. wrote exactly all.. like numbers and null as it is
 * 5. Serialization is Binary Tree to String
 * 6. Deserialization is String to Binary Tree
 * 
 * checkout this from leetcode too
 * 
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
 * Your Codec object will be instantiated and called as such:
 * - Codec ser = new Codec();
 * - Codec deser = new Codec();
 * - TreeNode ans = deser.deserialize(ser.serialize(root));
 * 
 * 
 * Pattern :
 * 
 * Serialize Binary to String
 * 1. will use BFS approach here -> queue to store root and when we pop it will
 * store values in string for null will add it in string not in queue
 * 2. will use StringBuilder to store the values
 * 
 * Deserialize String to Binary
 * 1. will use BFS approach here -> queue to store root and when we pop it will 
 * start adding values at root root.left and root.right
 * 2. will use StringBuilder to store the values
 * 3. will use same methode we use for helper function of building tree 
 * 
 * 
 * Pseudo Code :
 * 
 * function serialize(TreeNode root){
 * 
 *      if(root == null) return "";
 * 
 *      StringBuilder string = new StringBuilder
 * 
 *      queue = new LinkedList
 *      queue.add(root)
 *      string.append(root.val)
 * 
 *      while(!queue.isEmpty){
 *              
 *          node = queue.pop()
 * 
 *          if(node.left!= null){
 *              queue.add(node.left)
 *              string.append(node.left.val)
 *          }
 *          else{
 *              string.append("null")
 *          }
 *          
 *          if(node.right!= null){
 *              queue.add(node.right)
 *              string.append(node.right.val)
 *          }
 *          else{
 *              string.append("null")
 *          }
 *      }
 * 
 *      return string
 * }
 * 
 * 
 * function deserialize(string) {
 *  
 *      // Base Case 
 *      if(string.size() == 0) return null;
 *      
 *      //need to split the string and convert it into String array
 *      String[] stringSplit = string.split("")
 * 
 *      root = new TreeNode(stringSplit[0])
 *      
 *      queue = new linkedList
 *      queue.add(root)
 *      
 *      index = 0
 *      
 *      while(!queue.isEmpty){
 *          
 *          TreeNode node = queue.pop()
 *          
 *          if(stringSplit[index] != "null"){
 *              node.left = new TreeNode(stringSplit[index])
 *              queue.add(node.left)
 *          }
 *          else {
 *              node.left = null
 *          }
 *          index++
 * 
 *          if(stringSplit[index] != "null"){
 *              node.right = new TreeNode(stringSplit[index])
 *              queue.add(node.right)
 *          }
 *          else{
 *              node.right = null
 *          }
 *          index++;
 *          
 *      }
 * 
 *      return root;
 * }
 * 
 * 
 * 
 */