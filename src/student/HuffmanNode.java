package student;

public class HuffmanNode {
    /**
     * Huffman Node representing 0 in the tree.
     */
    private HuffmanNode zero;
    /**
     * Huffman Node representing 1 in the tree.
     */
    private HuffmanNode one;
    /**
     * Represents the char in the tree.
     * Note: only leaf nodes should have data and
     * zero and one should be null.
     */
    private Character data;

    // non-leaf Node.
    /**
     * Creates a Node with 0 and 1.
     * @param zero -- HufmanNode representing 0 in the tree.
     * @param one -- HufmanNode representing 1 in the tree.
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.zero = zero;
        this.one = one;
        this.data = null;
    }

    /**
     * HuffmanNode with a char as its data meaning
     * it is a leaf node.
     * @param data -- a char representing the data of the node.
     */
    public HuffmanNode(char data) {
        this.data = data;
        this.zero = null;
        this.one = null;
    }

    /**
     * 0 argument constructor making
     * everything point to null.
     */
    public HuffmanNode() {
        this.zero = null;
        this.one = null;
        this.data = null;
    }

    /**
     * Getter method for Zero.
     * @return -- HuffmanNode zero.
     */
    public HuffmanNode getZero() {
        return this.zero;
    }

    /**
     * Getter method for One.
     * @return -- HuffmanNode one
     */
    public HuffmanNode getOne() {
        return this.one;
    }

    /**
     * Getter method for the node's data.
     * @return -- a Character representing the data.
     */
    public Character getData() {
        return this.data;
    }

    /**
     * Set the zero of a node.
     * @param zero -- HuffmanNode zero you want a node to set to.
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * Set the one of a node.
     * @param one -- HufmanNode one you want a node to set to.
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * Set the data of a node.
     * @param data -- char data.
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * Check if a node is a leaf or not.
     * @return -- boolean value indicating the leaf status of a node.
     */
    public boolean isLeaf() {
        return this.zero == null && this.one == null && this.data != null;
    }

    /**
     * Checks if a node is valid according to HuffmanNode Rules.
     * @return -- boolean value indicating if a node is valid or not.
     */
    public boolean isValidNode() {
        if (isLeaf()) {
            // Leaf node should have data and no children
            return true;
        }
        return this.data == null && this.zero != null && this.one != null;
    }

    /**
     * Checks if the current node and its descendant nodes form a valid
     * tree structure. A valid tree structure: the current node must be
     * a valid node and each descendant node (zero and one) must also
     * form a valid tree structure.
     * @return -- true if the tree structure starting from the current node is valid
     * else false.
     */
    public boolean isValidTree() {
        // Check if this node is valid
        if (!isValidNode()) {
            return false;
        }
        // Recursively check validity of descendant nodes
        if (zero != null && !zero.isValidTree()) {
            return false;
        }
        if (one != null && !one.isValidTree()) {
            return false;
        }
        return true;
    }
}
