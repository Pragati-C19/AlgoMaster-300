import java.util.*;

public class FindDuplicateSubtrees {
    
}

/*
 * 
 *  * Few Important things to understand here
 * 
 * 1. will use String + Subtree Serialization + DFS + Hashing format
 * 
 * 2. Why we use string instead of array to store currSubtree?
 * - You can’t use an array as a Map or Set key directly unless you override equality and hashcode.
 * - String can easily represent tree structure as a serialized format.
 * - Example: a subtree with root 1, left child 2, and right child 3 → "1,2,#,#,3,#,#"
 * - That string captures both structure and value in a unique, serializable way.
 * - Arrays can't capture structure unless you also include nulls and positions carefully.
 * 
 * 3. What is subtree Serialization?
 * - A subtree serialization is a string that represents the structure and values of a tree.
 * - It is a way to represent a tree as a string, which can be used to compare trees
 * - If two subtrees are structurally and value-wise identical, then their serialized form should be exactly the same.
 * 
 * 4. With a HashMap<String, Integer>, you can track the frequency of each subtree.
 * - This lets you add it to the result list only the first time you see a duplicate
 * - Avoid adding duplicates more than once.
 * 
 * Intuitions :
 * 
 * 1. Root is give we need to fing duplicate subtree
 * 2. Condition :
 * - Tree are duplicate if they have same stucture with same node values
 * 
 * Pattern :
 * 
 * 1. We need to use map to check if we found duplicate or not
 * 2. result to store all subtrees
 * 3. Recursion call
 * - Base Case : if(root == null) return null
 * - Traverse the tree to left side and store the subtree in map
 * - Traverse the tree to right side and store the subtree in map
 * - if(duplicateMap.values(currSubtree) == 2) -> frequency of currSubTree is 2 means we found duplicate result.add(currSubtree)
 * - else -> add node to currSubtree and map duplicateMap.put(currSubtree)
 * - return root
 * 4. return result
 * 
 * Serialize Binary to String
 * 1. will use BFS approach here -> queue to store root and when we pop it will
 * store values in string for null will add it in string not in queue
 * 2. will use StringBuilder to store the values
 * 
 * 
 * Pseudo Code :
 * 
 * 
 * 
 * // Globally Declare variables
 * map<String, Integer> duplicateMap
 * result;
 * 
 * function findDuplicateSubtrees(root) {
 * 
 *      result = new ArrayList
 *      duplicateMap = new HashMap
 *      
 *      StringBuilder currString = new Stringbuilder
 * 
 *      postOrder(root, currString)
 * 
 *      return result
 * }
 * 
 * function String postOrder(root, currString){
 * 
 *      // Base Case :
 *      if(root == null) return currString.append("#")
 * 
 *      // Recur left side
 *      postOrder(root.left)
 * 
 *      // Recur right side
 *      postOrder(root.right)
 * 
 *      // Visit node
 *      if(duplicateMap.values(currString) == 2){
 *          result.add(new Array(currString))
 *          duplicateMap.values(currString)++
 *      }
 * 
 *      currString.append(root.val)
 *      duplicateMap.put(currString)
 *      
 *      return currString
 *      
 * }
 * 
 */