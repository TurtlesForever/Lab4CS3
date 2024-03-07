public class Lab4CS3 {


		public static void main(String[] args) {
			AVLTree tree = new AVLTree();
			 
			 /* Constructing tree given in the above figure */
		     tree.root = tree.insert(tree.root, 20);
		     tree.root = tree.insert(tree.root, 10);
		     tree.root = tree.insert(tree.root, 40);
		     tree.root = tree.insert(tree.root, 25);
		     tree.root = tree.insert(tree.root, 30);
		     tree.root = tree.insert(tree.root, 35);
			tree.root = tree.insert(tree.root, 45);
			tree.root = tree.insert(tree.root, 50);
			tree.root = tree.insert(tree.root, 15);
			tree.root = tree.insert(tree.root, 5);

		     /* he AVL Tree after deletion of 10
		            30
		           /  \
		         20    40
		         / \   / \
		       10  25 35  45
		       / \          \
		      5   15         50
		    
			*/
		  

			System.out.println("Inorder traversal" +
					   " of constructed tree is : ");
		     tree.inOrder(tree.root);
			System.out.println();
			System.out.println("Maximum value in the tree is "+tree.maxValueNode(tree.root).key);
			System.out.println("Minimum value in the tree is "+tree.minValueNode(tree.root).key);
			
			System.out.println("my Red Black Tree");
			// let us try to insert some data into tree and try to visualize the tree as well as traverse.
	        RedBlackTree t = new RedBlackTree();
	        int[] arr = {1,4,6,3,5,7,8,2,9};
	        for(int i=0;i<9;i++)
	        {
	            t.insert(arr[i]);
	            System.out.println();
	            t.inorderTraversal();
	        }
	       
	       
		}
		
	

	}

	/**
	This class implements an AVL tree with integer nodes or reb/black tree that supports
	insertion operation. It doesn't have a deletion method.
	*/



	class Node {
	 int key; // only integer data for this example
	 int height; // the height of this node in the tree
	 Node left, right, parent; // child references
	 char color;//color. either 'R' or 'B'.
	 
	 Node(int d) {
	     key = d;
	     height = 1;
	     this.left = null; // left subtree
	     this.right = null; // right subtree
	     this.color = 'R'; // colour . either 'R' or 'B'
	     this.parent = null; // required at time of rechecking.
	 }
	}


	class AVLTree {

	 Node root;

	 
	 /* Given a non-empty binary search tree, return the
	 node with minimum key value found in that tree.
	 Note that the entire tree does not need to be
	 searched. */
	 Node minValueNode(Node node)
	 {
	     //finish code here

	     return null; //replace this line
	 }
	//Function to find the node with maximum value
	//i.e. rightmost leaf node
	 Node maxValueNode(Node node)
	{ 
		//finish code here

	     return null; //replace this line
	}
	 
	
	 
	 // method to return the height of the tree
	 int height(Node N) {
	     if (N == null)
	         return 0;
	     return N.height;
	 }
	 //method to return max of two integers
	 int max(int a, int b) {
	     return (a > b) ? a : b;
	 }

	 /* Method to right-rotate a subtree rooted at node y. 
	    Returns the new root of the tree
	  */
	 /**

	  y                               x
	 / \     Right Rotation          /  \
	x   T3   - - - - - - - >        T1   y 
	/ \                                  / \
	T1  T2                               T2  T3


	*/    


	 Node rightRotate(Node y){
		Node x = y.left;
		Node T2 = x.right;
		x.right = y;
		y.left = T2;

		/**
		   Which nodes have changed height?
		   z and y only
		*/
		y.height = max( height(y.left), height(y.right)) + 1;
		x.height = max( height(x.left), height(x.right)) + 1;

		/** return new root of the tree */
		return x;
	 }
	 

	 
	 /* Method to left-rotate a subtree rooted at node x. 
	    Returns the new root of the tree
	  */

	 /**
	            x                               y
	           / \                             / \
	         T1   y   Left Rotation           x   T3 
	             / \  -------------->        / \   
	           T2  T3                      T1   T2   

	 */
	 
	 Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;
		
	     // Perform rotation
	     y.left = x;
	     x.right = T2;
		/**
		   Which nodes have changed height?
		   x and y only
		*/	
	     x.height = max(height(x.left), height(x.right)) + 1;
	     y.height = max(height(y.left), height(y.right)) + 1;
		
	     // Return the new root
	     return y;
	 }


	 /**
	    Method to return the balance of a Node
	 */
	 int getBalance(Node N) {
	     if (N == null)
	         return 0;
	     return height(N.left) - height(N.right);
	 }

	 /** Method to insert a new Node with key key 
		in the BST rooted at Node
		
		Returns a reference to the  inserted node.
	 */
	 
	 Node insert(Node node, int key) {

	     /*   Perform a  normal BST insert operation */
		
	     if (node == null)
	         return (new Node(key));

	     if (key < node.key)
	         node.left = insert(node.left, key);
	     else if (key > node.key)
	         node.right = insert(node.right, key);
	     else // duplicate key
	         return node;

	     /*  Update height of this ancestor node */
		
	     node.height = 1 + max(height(node.left),
	                           height(node.right));

	     /* Get the balance factor of this ancestor
		   node to check whether it became unbalanced 
		*/
	     int balance = getBalance(node);

	     /** 
		    If this node becomes unbalanced, then there
		    are 4 cases to deal with. 
		*/
		
		// Left Left Case
		/* z is the first node that is out of balance traveling 
		   up from the newly inserted node w.
		   y is left child of z, x is left child of y.
		   In this case, balance of the node will be greater than 1.
		      z
	          /
	         y
	        /
	       x
	      .
	      .
	      w

		*/
		
	     if (balance > 1 && key < node.left.key)
	         return rightRotate(node);
		
	     // Right Right Case

		/* z is the first node that is out of balance traveling 
		   up from the newly inserted node w.
		   y is right child of z, x is right child of y.
		   In this case, balance of the node will be less than -1.
	             z
	              \
	               y
	                \
	                 x
	                  .
	                  .
	                  w
		*/



		if (balance < -1 && key > node.right.key)
	         return leftRotate(node);

	     // Left Right Case

		/* z is the first node that is out of balance traveling 
		   up from the newly inserted node w.
		   y is left child of z, x is right child of y.
		   In this case, balance of the node will be greater than 1.         
	                    z
	                   /
	                  y
	                   \
	                    x
	                    . 
	                    .
	                    w

		*/
		
		
	     if (balance > 1 && key > node.left.key) {
	         node.left = leftRotate(node.left);
	         return rightRotate(node);
	     }

	     // Right Left Case

		/* z is the first node that is out of balance traveling 
		   up from the newly inserted node w.
		   y is right child of z, x is left child of y.
		   In this case, balance of the node will be less than -1.       
	                    z
	                     \
	                      y
	                     /
	                    x
	                    . 
	                    .
	                    w

		*/
		
		
	     if (balance < -1 && key < node.right.key) {
	         node.right = rightRotate(node.right);
	         return leftRotate(node);
	     }

	     /* finally return */
	     return node;
	 }

	 
	 
	void preOrder(Node node) {
	     if (node != null) {
	         System.out.print(node.key + " ");
	         preOrder(node.left);
	         preOrder(node.right);
	     }
	 }

	 
	void inOrder(Node node) {
	     if (node != null) {
	         inOrder(node.left);
		    System.out.print(node.key + " ");
	         inOrder(node.right);
	     }
	}
	 
	void postOrder(Node node) {
	     if (node != null) {
	         postOrder(node.left);
	         postOrder(node.right);
		    System.out.print(node.key + " ");
	     }
	}
	 
	} 

	/*Red/Black Tree*/
	class RedBlackTree
	{
	    public Node root;//root node
	    public RedBlackTree()
	    {
	        super();
	        root = null;
	    }
	 
	    // this function performs left rotation
	    Node rotateLeft(Node node)
	    {
	    	//finish code here

		     return null; //replace this line
	    }
	    //this function performs right rotation
	    Node rotateRight(Node node)
	    {
	    	//finish code here

		     return null; //replace this line
	    }
	 
	 
	    // these are some flags.
	    // Respective rotations are performed during traceback.
	    // rotations are done if flags are true.
	    boolean ll = false;
	    boolean rr = false;
	    boolean lr = false;
	    boolean rl = false;
	    // helper function for insertion. Actually this function performs all tasks in single pass only.
	    Node insertNode(Node root, int data)
	    {
	        // f is true when RED RED conflict is there.
	        boolean f=false;
	         
	        //recursive calls to insert at proper position according to BST properties.
	        if(root==null)
	            return(new Node(data));
	        else if(data<root.key)
	        {
	            root.left = insertNode(root.left, data);
	            root.left.parent = root;
	            if(root!=this.root)
	            {
	                if(root.color=='R' && root.left.color=='R')
	                    f = true;
	            }
	        }
	        else
	        {
	            root.right = insertNode(root.right,data);
	            root.right.parent = root;
	            if(root!=this.root)
	            {
	                if(root.color=='R' && root.right.color=='R')
	                    f = true;
	            }
	        // at the same time of insertion, we are also assigning parent nodes
	        // also we are checking for RED RED conflicts
	        }
	 
	        // now lets rotate.
	        if(this.ll) // for left rotate.
	        {
	            root = rotateLeft(root);
	            root.color = 'B';
	            root.left.color = 'R';
	            this.ll = false;
	        }
	        else if(this.rr) // for right rotate
	        {
	            root = rotateRight(root);
	            root.color = 'B';
	            root.right.color = 'R';
	            this.rr  = false;
	        }
	        else if(this.rl)  // for right and then left
	        {
	            root.right = rotateRight(root.right);
	            root.right.parent = root;
	            root = rotateLeft(root);
	            root.color = 'B';
	            root.left.color = 'R';
	 
	            this.rl = false;
	        }
	        else if(this.lr)  // for left and then right.
	        {
	            root.left = rotateLeft(root.left);
	            root.left.parent = root;
	            root = rotateRight(root);
	            root.color = 'B';
	            root.right.color = 'R';
	            this.lr = false;
	        }
	        // when rotation and recolouring is done flags are reset.
	        // Now lets take care of RED RED conflict
	        if(f)
	        {
	            if(root.parent.right == root)  // to check which child is the current node of its parent
	            {
	                if(root.parent.left==null || root.parent.left.color=='B')  // case when parent's sibling is black
	                {// perform certaing rotation and recolouring. This will be done while backtracking. Hence setting up respective flags.
	                    if(root.left!=null && root.left.color=='R')
	                        this.rl = true;
	                    else if(root.right!=null && root.right.color=='R')
	                        this.ll = true;
	                }
	                else // case when parent's sibling is red
	                {
	                    root.parent.left.color = 'B';
	                    root.color = 'B';
	                    if(root.parent!=this.root)
	                        root.parent.color = 'R';
	                }
	            }
	            else  
	            {
	                if(root.parent.right==null || root.parent.right.color=='B')
	                {
	                    if(root.left!=null && root.left.color=='R')
	                        this.rr = true;
	                    else if(root.right!=null && root.right.color=='R')
	                        this.lr = true;
	                }
	                else
	                {
	                    root.parent.right.color = 'B';
	                    root.color = 'B';
	                    if(root.parent!=this.root)
	                        root.parent.color = 'R';
	                }
	            }
	            f = false;
	        }
	        return(root); 
	    }
	 
	    // function to insert data into tree.
	    public void insert(int data)
	    {
	        if(this.root==null)
	        {
	            this.root = new Node(data);
	            this.root.color = 'B';
	        }
	        else
	            this.root = insertNode(this.root,data);
	    }
	    // helper function to print inorder traversal
	    void inorderTraversal(Node node)
	    {
	        if(node!=null)
	        {
	            inorderTraversal(node.left);
	            System.out.printf("%d ", node.key);
	            inorderTraversal(node.right);
	        }
	    }
	    //function to print inorder traversal
	    public void inorderTraversal()
	    {
	        inorderTraversal(this.root);
	    }
	   
	}