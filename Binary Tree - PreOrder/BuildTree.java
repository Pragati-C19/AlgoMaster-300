import java.util.*;

public class BuildTree {
    
}


/*
 * 
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
 * 
 * Pseudo Code :
 * 
 * 
 * 
 */