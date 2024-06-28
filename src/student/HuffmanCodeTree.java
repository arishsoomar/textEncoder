package student;

import provided.BinarySequence;

public class HuffmanCodeTree {
    /**
     * HuffmanNode representing the root of the tree.
     */
    private HuffmanNode root;

    /**
     * Constructor for the tree in which the root is assigned.
     * @param root -- HuffmanNode that will be the root of the tree.
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Second constructor that takes into account a codebook making
     * it a Huffman Code Tree.
     * @param codebook -- a HufmanCodeBook to make the tree based off.
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        this.root = new HuffmanNode();

        char[] characters = codebook.getAllCharacters();

        for (char c : characters) {
            BinarySequence sequence = codebook.getSequence(c);
            put(sequence, c);
        }
    }

    /**
     * Checks to see if the tree is a valid tree by
     * calling isValidTree method on the root.
     * @return -- boolean true if it is valid, otherwise false.
     */
    public boolean isValid() {
        return this.root.isValidTree();
    }

    /**
     * Putting a seq and char into the huffan tree according to
     * structural rules.
     * @param seq -- BinarySequence of the letter added.
     * @param letter -- the char that is being added.
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode node = root;
        for (boolean bit : seq) {
            if (bit) {
                if (node.getOne() == null) {
                    node.setOne(new HuffmanNode());
                }
                node = node.getOne();
            } else {
                if (node.getZero() == null) {
                    node.setZero(new HuffmanNode());
                }
                node = node.getZero();
            }
        }
        node.setData(letter);
    }

    /**
     * Given a BinarySequence, this method decodes it into a
     * String according to the huffman code book.
     * @param s -- the BinarySequence being decoded.
     * @return -- decoded String.
     */
    public String decode(BinarySequence s) {

        StringBuilder decodedString = new StringBuilder();
        HuffmanNode node = root;
        for (boolean bit : s) {
            if (bit) {
                node = node.getOne();
            } else {
                node = node.getZero();
            }
            if (node.isLeaf()) {
                decodedString.append(node.getData());
                node = root;
            }
        }
        return decodedString.toString();
    }
}
