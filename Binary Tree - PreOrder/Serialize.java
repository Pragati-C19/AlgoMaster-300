import java.util.*;

public class Serialize {

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
 *      
 * 
 * }
 * 
 * 
 * 
 */