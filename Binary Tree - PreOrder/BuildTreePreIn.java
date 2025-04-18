import java.util.*;

public class BuildTreePreIn {
 
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

    // Globally Assign preIndex to avoid stack overflow for more infor check comments at last
    private int preIndex;

    // Driver Function
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        preIndex = 0;

        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, inorder, inorderMap, 0, (inorder.length - 1));
    }

    // Recursion Function : to build tree
    private TreeNode build(int[] preorder, int[] inorder, Map<Integer, Integer> inorderMap, int inorderStart, int inorderEnd){

        // Base Case :
        if (inorderStart > inorderEnd) {
            return null;
        }

        // We are getting root from preorder so added it in rootVal
        int rootVal = preorder[preIndex];
        preIndex++;
        System.out.println(" rootVal : " + rootVal);

        // Find root index in inorder
        int rootIndex = inorderMap.get(rootVal);
        System.out.println(" rootIndex in Inorder : " + rootIndex);

        // Storing that rootVal in TreeNode
        TreeNode root = new TreeNode(rootVal);

        // Checking left and right
        root.left = build(preorder, inorder, inorderMap, inorderStart, rootIndex - 1);
        root.right = build(preorder, inorder, inorderMap, rootIndex + 1, inorderEnd);

        return root;
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

        BuildTreePreIn solution = new BuildTreePreIn();

        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        System.out.println("Output1 : " + printTreeAsArrayFormat(solution.buildTree(preorder1, inorder1)) + "\n");

        int[] preorder2 = { -1 };
        int[] inorder2 = { -1 };
        System.out.println("Output2 : " + printTreeAsArrayFormat(solution.buildTree(preorder2, inorder2)));

    }

}


/*
 * 
 * Core Pattern :
 * 
 * - First create map for inorder to store value and index
 * - set global starting index or preIndex or postIndex
 * - function should have (int[] A, int[] B, int bStart, int bEnd, int aIndex)
 * - store root took by preIndex or postIndex in rootVal 
 * - Find index of root in inorder
 * - calculate leftSize and right Size 
 * - recure root.left and roo.right
 * - return root

 * 
 * Intuitions :
 * 
 * 1. Given Two arrays preorder and inorder
 * 2. preorder is the preorder traversal of binary tree (root -> left - > right)
 * 3. inorder is the inorder traversal of binary tree (left - > root -> right)
 * 4. return Binary tree
 * 
 * Pattern :
 * 
 * 1. Array of inorder will get shrink and breaks in leftSubTree and rightSubTree
 * 2. Array of preorder only tell use abt root...  so traverse full array one by one
 * 3. after getting root check at what index that root is in inorder Array
 * 4. if root is at index i then leftSubTree will be from 0 to i
 * 5. rightSubTree will be from i+1 to end of inorder Array
 * 6. Shrink Trees
 * 
 * eg. preorder = [3,9,10,20,15,11,7], inorder = [10,9,3,11,15,20,7]
 * 1. preorder(3) 
 * In inorder, 3 is at index 2.
 * So: Left Inorder = [10, 9] | Right Inorder = [11, 15, 20, 7]
 * 
 * 2. preorder(9)
 * In Left Inorder(3), 9 is at index 1. and size of array is 2
 * So: Left Inorder = [10] | Right Inorder = []
 * 
 * 3. preorder(10)
 * In Left Inorder(9), 10 is at index 0. and and size of array is 1
 * So: Left Inorder = [] | Right Inorder = []
 * 
 * Now we go back to 3 and handle the right subtree, which was [11, 15, 20, 7] in inorder.
 * 
 * 4. preorder(20)
 * In Right Inorder(3), 20 is at index 2.
 * So : Left Inorder = [11, 15] | Right Inorder = [7]
 * 
 * 5. preorder(15)
 * In Left Inorder(20), 15 is at index 1. and size of array is 2 
 * So : Left Inorder = [11] | Right Inorder = []
 * 
 * 6. preorder(11)
 * In Left Inorder(15), 11 is at index 0. and size of array is 1
 * So : Left Inorder = [] | Right Inorder = []
 * 
 * 7. preorder(7)
 * In Right Inorder(20), 7 is at index 0. and size of array is 1
 * So : Left Inorder = [] | Right Inorder = []
 * 
 * 
 * Why do we need preIndex or postIndex as Global / Shared?
 * 
 * - Traversal Arrays Are Sequential	
 *      ○ Preorder and postorder arrays tell us the order of nodes as we traverse the tree.	
 *      ○ So we must visit elements one by one in the exact order they appear, like reading a list from start to end (or end to start for postorder).	
 *      ○ That means:
 *          → Preorder: go left-to-right (start from 0, move to n)
 *          → Postorder: go right-to-left (start from n-1, move to 0)
 * 
 * - Recursion Needs to Remember the Index
 *      ○ Every time a recursive call is made, we need the next root value from the traversal array.
 *      ○ Example (preorder):
 *          → first root = preorder[0]
 *          → next root = preorder[1], then preorder[2], etc.
 *	
 *		○ If we just write preIndex + 1 or preIndex++ inside the function call:
 *			→ We're just passing a copy of that value to the recursive call.
 *			→ The change does NOT reflect back to the outer call.
 *			→ That means each recursive call will still think it's on the same index → leads to infinite recursion or wrong trees.
 *	
 *		○ So we need to store index in a shared variable, which:
 *			→ All recursive calls can access and modify
 *			→ Ensures the traversal happens exactly in order
 *	
 *  - Why "Global" or "Shared"?	
 *		○ Option A: Class Variable
 *			→ private int preIndex = 0; // Shared among all recursive calls
 *
 *		○ Option B: Wrapper (Array or Object)
 *			 int[] preIndex = new int[]{0}; // preIndex[0] gets updated and shared
 *
 *	- What If I Use preIndex++ in Function Call?
 *	
 *		○ root.left = build(preorder, ... preIndex++);
 *		
 *		○ This does not work because:
 *			→ Java evaluates arguments before the method call.
 *			→ So preIndex++ passes the current value, then increments after.
 *			→ The function still gets the old value, not the new one.
 *			
 *		○ Same issue with just preIndex + 1 → that’s a copy, not an update.
 *
 *
 *	- Why This Works for Preorder, Postorder, Inorder All?
 *	
 *		○ Preorder: move forward → need global index from start (0 → n)
 *		○ Postorder: move backward → need global index from end (n-1 → 0)
 *		○ Inorder: doesn't need global index because it's only used for finding subtree bounds, not traversal order.
 * 
 * 
 * Pseudo Code :
 * 
 * 1. Without using map
 * function buildTree(preorder, inorder){
 *      
 *      Integer[] result = new ArrayList
 *      
 *      // Tracking how far preorder is gone
 *      preorderIndex = 0
 * 
 *      Integer[] treeArray = dfs(preorder, inorder, preorderIndex, result)
 *      
 *      TreeNode rootOfBinaryTree = buildTreeWithArray(treeArray)
 *      
 *      return rootOfBinaryTree;
 * 
 * }
 * 
 * function TreeNode buildTreeWithArray(Integer[] nodes) {
 * 
 *      // Node is empty
 *      if (nodes.length == 0 || nodes[0] == null) return null;
 * 
 *      TreeNode root = new TreeNode(nodes[0]);
 *      Queue<TreeNode> queue = new LinkedList<>();
 *      queue.add(root);
 * 
 *      int i = 1;  // Start from second element
 * 
 *      while (!queue.isEmpty() && i < nodes.length) {
 * 
 *          TreeNode parent = queue.poll();
 * 
 *          // Assign left child
 *          if (nodes[i] != null) {
 *              parent.left = new TreeNode(nodes[i]);
 *              queue.add(parent.left);
 *          }
 *          i++;
 *          
 *          // Assign right child (check if there's still an element)
 *          if (i < nodes.length && nodes[i] != null) {
 *              parent.right = new TreeNode(nodes[i]);
 *              queue.add(parent.right);
 *          }
 *          i++;
 *      }
 * 
 *      return root;
 * }
 * 
 * 
 * function dfs(int[] preorder, int[] inorder, int start, int end, int preorderIndex, Integer[] result) {
 *      
 *      // if (inorder.length == 0) 
 *      // That always checks the full length. 
 *      // But we are working with a segment from start to end.
 *      if(start > end){
 *          result.add(null)
 *          return;
 *      }
 *      
 * 
 *      for(int i = start; i <= end; i++){
 *          
 *          if(preorder[preorderIndex] == inorder[i]){
 *              leftInorder = dfs( preorder, inorder, start, i - 1, preorderIndex++, result)
 *              rightInorder = dfs( preorder, inorder, i + 1, end, preorderIndex++, result)
 *          } 
 *       
 *      }
 *      
 *      return result.add(leftInorder).add(rightInorder);
 * }
 * 
 * 
 * 2. with Core Pattern of mapping
 * 
 * function buildTree( int[] preorder, int[] postorder){
 *      
 *      preorderIndex = 0
 *      
 *      inorderMap = new Hashmap
 *      
 *      // storing value and index of inorder in map
 *      for(int i = 0; i < inorder.length; i++){
 *          map.put(inorder[i], i)
 *      }
 * 
 *      return build(preorder, inorder, inorderMap, 0, (inorder.length - 1), preorderIndex)
 * }
 * 
 * function build(int[] preorder, int[] inorder, Map<Integer, Integer> inorderMap, int inorderStart, int inorderEnd, int preorderIndex){
 *      
 *      // Base Case :
 *      if(inorderStart > inorderEnd) return null;
 * 
 *      // We are getting root from preorder so added it in rootVal
 *      int rootVal = preorder[preIndex]
 *      
 *      // Find root index in inorder 
 *      int rootIndex = inorderMap.get(rootVal)
 *      
 *      // Storing that rootVal in TreeNode root
 *      TreeNode root = new TreeNode(rootVal)
 * 
 *      // Check left and right now
 *      root.left = build(preorder, inorder, inorderMap, inorderStart, rootIndex - 1 , preIndex++);
 *      root.right = build(preorder, inorder, inorderMap, rootIndex + 1, inorderEnd, preIndex++);
 *      
 *      return root;
 * }
 * 
 *  
 * 
 */