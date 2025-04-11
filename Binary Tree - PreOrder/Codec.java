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
        
        //Base Case :
        if (root == null) {
            System.out.println("root is null so String is also Empty");
            return "";
        }

        StringBuilder serializeString = new StringBuilder();
        serializeString.append(root.val);
        System.out.println("At start String Values are : " + serializeString);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            
            TreeNode node = queue.poll();

            // Check left nodes and added it in string
            if (node.left != null) {
                
                serializeString.append(", ").append(node.left.val);
                System.out.println("    [IF : Left!=null] String Values are : " + serializeString);

                queue.add(node.left);

            }
            else {

                serializeString.append(", null ");
                System.out.println("    [IF : Left=null] String Values are : " + serializeString);
            
            }

            // Check right node and added it in string
            if (node.right != null) {
                
                serializeString.append(", ").append(node.right);
                System.out.println("    [IF : Right!=null] String Values are : " + serializeString);

                queue.add(node.right);
            
            }
            else {

                serializeString.append(", null ");
                System.out.println("    [IF : Right=null] String Values are : " + serializeString);

            }

        }

        return serializeString.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        return null;
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

    // Helper Function : to build a tree from an array (for testing)
    public static TreeNode buildTree(Integer[] nodes, int index) {
        
        // Base Case: if index is out of bounds or node is null
        if (index >= nodes.length || nodes[index] == null) return null;

        TreeNode root = new TreeNode(nodes[index]);

        // Recursively build left and right children
        root.left = buildTree(nodes, 2 * index + 1);
        root.right = buildTree(nodes, 2 * index + 2);

        return root;
    }

    public static void main (String[] args){

        Codec ser = new Codec();
        Codec deser = new Codec();

        Integer[] treeArray1 = {1, 2, 3, null, null, 4, 5};
        TreeNode root1 = buildTree(treeArray1, 0);
        String ansForSerialize1 = ser.serialize(root1);
        System.out.println("Output For Serialize : " + ansForSerialize1 + "\n");
        // TreeNode ansForDeserialize1 = deser.deserialize(ansForSerialize1);
        // System.out.println("Output1 : " + printTreeAsArrayFormat(ansForDeserialize1) + "\n");

        Integer[] treeArray2 = {};
        TreeNode root2 = buildTree(treeArray2, 0);
        String ansForSerialize2 = ser.serialize(root2);
        System.out.println("Output For Serialize : " + ansForSerialize2 + "\n");
        // TreeNode ansForDeserialize2 = deser.deserialize(ansForSerialize2);
        // System.out.println("Output2 : " + printTreeAsArrayFormat(ansForDeserialize1) + "\n");

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