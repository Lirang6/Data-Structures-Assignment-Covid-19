package coronatree;

public class AVLNode {	
	Person data;		// The data in the node
	AVLNode parent;		// The parent
	AVLNode left;       // Left child
	AVLNode right;      // Right child
	int height;       	// Height

	/**
	 * A standard constructor, sets all pointers to null.
	 * 
	 * @param p - the data of the node.
	 */
    AVLNode(Person p) {
    	this.data = p;
		this.parent = null;
		this.left = null;
		this.right = null;
		this.height = 0;
    }
    
    /**
     * A standard constructor
     * 
     * @param p - the data of the node.
     * @param lt - the left child.
     * @param rt - the right child.
     * @param parent - the parent.
     */
    AVLNode(Person p, AVLNode lt, AVLNode rt, AVLNode parent){
		this.data = p;
		this.parent = parent;
		this.left = lt;
		this.right = rt;
		this.height = Math.max(this.getLHeight(), this.getRHeight()) + 1;
    }

    public int getLHeight(){
    	if (this.left != null) { return this.left.height; }
    	else return -1;
	}

	public int getRHeight(){
		if (this.right != null) { return this.right.height; }
		else return -1;
	}

    public int bf(){
    	return this.getLHeight() - this.getRHeight();
	}

    public String toString(){
    	return this.data.toString();
    }

}
