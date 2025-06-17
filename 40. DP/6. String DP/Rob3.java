import java.util.*;

public class Rob3 {
    
}

/*
 * Intuitions :
 
   1. Thief has found a new place for his thievery again
   2. There is only one entrance to this areas which is root
   3. other than root there are each houses which has only one parent house
   4. all houses form a binary tree 
   5. if two directly linked houses are broken on same night it will call police automatically
   6. we have given roots of tree 
   7. need to return maximum amount of money the thief can rob without alerting the polic

 
 * Pattern :
 
   1. What should we add in dp?..
      - will store amount of money we got if we rob the currHouse
      - initially will store values for currHouse=0 and currHouse=1
         dp[0] = root[0].value
         dp[1] = max(root[0].value, root[1].value) 
      - will check if we can rob currHouse or not
         robCurrHouse = dp[i-2] + root[i].value
         dp[i] = max(dp[i-1], robCurrHouse)
      - This works only when houses are in a straight line, because:
         If you rob house i, you must skip i-1.
         So you're always looking 2 steps back.
      - In this problem we are dealing with a tree, not a line. So we can't use i-1 or i-2.
         A tree has parent-child relationships, not fixed positions.
         So we use a bottom-up DP on a tree — where for each node we store:
            int rob = node.val + left[0] + right[0];   // Rob this node, skip children
            int notRob = max(left[0], left[1]) + max(right[0], right[1]); // Don't rob this node, take max from children
      - In trees You rob = skip children
         In array You rob = skip prev

   2. what result array means ?
         res[0] = max money if we DON'T rob this node
         res[1] = max money if we DO rob this node

   3. Same for Right and Left nodes
         left[0] -> max money if you don't rob left child
         left[1] -> max money if you do rob left child

         right[0] -> max money if you don't rob right child
         right[1] -> max money if you do rob right child

   4. Now think abt how will u check values in binary tree
      - in dp we need to do bottom up approach 
         so will check post-order (left - right - root)
      
   
   ^ Dry Run
                   root
                     3
                   /   \
                  2     3
                    \    \
                     3    1 
      
               bottom-up build

                   [6,7]    <- root
                  /     \
               [3,2]     [1,3]
                  \         \
                 [0,3]     [0,1]


      - Step 1: Start from bottom-most leaf: 3 (left.right)

            node = 3 
            left = robTree(null)    ->  [0, 0]
            right = robTree(null)   ->  [0, 0]

            rob = 3 + 0 + 0 = 3
            notRob = max(0,0) + max(0,0) = 0

            [notRob, rob] = [0, 3]


      - Step 2: Node 2 (left child of root)

            node = 2
            left = robTree(null)    ->  [0, 0]
            right = robTree(3)      ->  [0, 3] (from Step 1)

            rob = 2 + 0 + 0 = 2
            notRob = max(0, 0) + max(0, 3) = 3

            [notRob, rob] = [3, 2]


      - Step 3: Node 1 (right.right)

            node = 1
            left = robTree(null)    ->  [0, 0]
            right = robTree(null)   ->  [0, 0]

            rob = 1 + 0 + 0 = 1
            notRob = max(0, 0) + max(0, 0) = 0

            [notRob, rob] = [0, 1]


      - Step 4: Node 3 (right child of root)

            node = 3
            left = robTree(null)    ->  [0, 0]
            right = robTree(1)      ->  [0, 1] (from Step 3)

            rob = 3 + 0 + 0 = 3
            notRob = max(0, 0) + max(0, 1) = 1

            [notRob, rob] = [1, 3]


      - Step 5: Node 3 (root)

            node = 3
            left = robTree(2)       ->  [3, 2] (from Step 2)
            right = robTree(3)      ->  [1, 3] (from Step 4)

            rob = 3 + 3 + 1 = 7
            notRob = max(3, 2) + max(1, 3) = 6

            [notRob, rob] = [6, 7]


 
 * Pseudo Code :
 
   function rob3(root) {
   
      -> Declare variable
         int[] result = new int[2]     - result will store values of notRob(0) and rob(1)
         
      -> call dfs recursion function 
         result = dfs(root)

      -> take max between both notRob and rob
         maxMoney = max(result[0], result[1])
   
      -> at the end return maxMoney Count 

   }

   function dfs (root) {
   
      Base Case :
         if node is null will return new int[]{0,0}

      -> check left and right
         left = dfs(root.left)
         right = dfs(root.right)
         
      -> Visit node now

         - why when we check rob we are taking non rob values of left and right?
            bcoz we don't want to rob adj houses means we don't want to rob child of currRoot

         rob = currNode + left[notRob] + right[notRob]
         notRob = max(left[notRob], left[rob]) + max(right[notRob], right[rob])

      -> return rob and notRob
      
   }



 */