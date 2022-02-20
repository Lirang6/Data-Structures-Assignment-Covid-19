package coronatree;

public class AVLTree {

    AVLNode root; 	// The tree root.
    int size;		// The size of the tree.

    /**
     * Construct an empty tree.
     */
    public AVLTree() {
       	this.root = new AVLNode(null);
       	this.size = 0;
    }
    
    /**
     * Returns the size of the tree.
     * 
     * @return the size of the tree.
     */
    public int size(){
    	return size;
    }
    
    /**
     * Returns the height of the tree.
     * Returns -1 if the tree is empty.
     * 
     * @return the height of the tree.
     */
    public int height(){
    	if (this.size == 0) return -1;
    	return this.root.height;
    }
    
    /**
     * Inserts into the tree; You may assume that every person has a unique ID number.
     * That is, no person will appear twice.
     * 
     * @param p - the person to insert.
     */
    public void insert(Person p) {
        AVLNode n = new AVLNode(p, null, null, null);
        this.size++;
        if (this.size == 1) {
            this.root = n;
            return;
        }
        AVLNode i = this.root;
        while ((i.right != null && i.data.id < p.id) || (i.left != null && i.data.id > p.id) ){
            if (p.id > i.data.id){
                i = i.right;
            } else {
                i = i.left;
            }
        }
        n.parent = i;
        if (p.id > i.data.id){
            i.right = n;
        } else { i.left = n; }
        for (AVLNode q = n.parent; q != null; q = q.parent){
            updateH1(q);
            if (Math.abs(q.bf()) > 1) {
                rebalance(q);
            } else{
                updateH1(q);
            }
        }
    }

    public void rebalance(AVLNode x){
        if (x.bf() > 1) {
            if(x.left.bf() < 0){
                leftRotation(x.left);
            }
            rightRotation(x);
        } else {
            if(x.right.bf() > 0){
                rightRotation(x.right);
            }
            leftRotation(x);
        }
    }
    public void updateH1(AVLNode x){
        x.height = Math.max(x.getLHeight(), x.getRHeight()) + 1;
    }

    public void updateH(AVLNode x){
        while(x != null){
            updateH1(x);
            x = x.parent;
        }
    }

    public void leftRotation(AVLNode x){
        AVLNode z = x.right;
        AVLNode t23 = z.left;
        x.right = t23;
        if (z.left != null) {
            t23.parent = x;
        }
        z.parent = x.parent;
        if( x.parent != null) {
            if (x.parent.left == x) {
                x.parent.left = z;
            }
            if (x.parent.right == x) {
                x.parent.right = z;
            }
        } else {
            this.root = z;
        }
        z.left = x;
        x.parent = z;
        updateH1(x);
        updateH1(z);;
        updateH(x.parent);
    }

    public void rightRotation(AVLNode x){
        AVLNode z = x.left;
        AVLNode t23 = z.right;
        x.left = t23;
        if (z.right != null) {
            t23.parent = x;
        }
        z.parent = x.parent;
        if( x.parent != null) {
            if (x.parent.left == x) {
                x.parent.left = z;
            }
            if (x.parent.right == x) {
                x.parent.right = z;
            }
        } else {
            this.root = z;
        }
        z.right = x;
        x.parent = z;
        updateH1(x);
        updateH1(z);;
        updateH(x.parent);
    }

    /**
     * Search for a person in the tree.
     * 
     * @param p the person to search for.
     * @return true iff 'p' is an element in the tree.
     */
    public boolean search(Person p) {
    	AVLNode n = this.root;
        while ( n.left != null || n.right != null){
            if (n.data.id == p.id) return true;
            if (n.data.id > p.id) n = n.left;
            else n = n.right;
        }
    	return false;
    }
    static int j=0;
    /**
     * Traverse the contents of this tree in an 'inorder' manner and return and array
     * containing the traversal of the tree.
     * 
     * @return a sorted array of the tree's content.
     */
    public Person[] inorder() {
        Person[] result = new Person[this.size];
        AVLNode n = this.root;
        j = 0;
        inorder(n, result);
        return result;
    }

    public void inorder(AVLNode n, Person[] result){
        if (n.left != null) {
            inorder(n.left, result);
        }
        result[j++] = n.data;
        if (n.right != null) {
            inorder(n.right, result);
        }
    }
}
