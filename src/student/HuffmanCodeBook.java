package student;

import provided.BinarySequence;

// you may implement any interface you want here
public class HuffmanCodeBook {
    /**
     * int tracking the number of elements in the arary.
     */
    private int length;
    /**
     * character array of our sorted characters according to ascii values.
     */
    private char[] charArray;
    /**
     * BinarySequence Array the indicies of the char array match the corresponding
     * sequence array indicies. So if c is at index 3, c's sequence is at index 3
     * as well.
     */
    private BinarySequence[] seqArray;

    /**
     * 0 argument constructor that allocates size 1
     * to both char and sequence arrays and assigns
     * the length to 0.
     */
    public HuffmanCodeBook() {
        charArray = new char[1];
        seqArray = new BinarySequence[1];
        this.length = 0;
    }
    /**
     * Adds the character and sequence maintaing alphabetical order
     * and making sure the indicies of both are the same, allowing
     * mapping capabilities.
     * @param c -- a char you want to add to the HuffmanCodeBook/Table
     * @param seq -- a sequence that corresponds to the character.
     */
    public void addSequence(char c, BinarySequence seq) {
        if (contains(c)) {
            seqArray[getIndexOf(c)] = seq;
        } else if (length == 0) {
            charArray[0] = c;
            seqArray[0] = seq;
            length++;
        } else {
            // if length = allocation size then resize both arrays.
            if (length == charArray.length) {
                reSize();
            }

            int currentIdx = 0;
            char currentValue = charArray[currentIdx];

            while (currentIdx < length && c > currentValue) {
                currentIdx++;
                if (currentIdx < length) {
                    currentValue = charArray[currentIdx];
                }
            }
            // shift all characters to the right so we can add the new char and seq.
            shiftRightAt(currentIdx);
            charArray[currentIdx] = c;
            seqArray[currentIdx] = seq;

            length++;
        }
    }
    /**
     * Checks if the letter is in the codebook/table.
     * @param letter -- a char you want to check if it is in the codebook.
     * @return -- boolean value indicating the existence of the char.
     */
    public boolean contains(char letter) {
        if (getIndexOf(letter) == -1) {
            return false;
        }
        return true;
    }

    /**
     * Gets the index of the letter in the codebook/table.
     * @param letter -- the char letter you want the index of.
     * @return -- an int representing the index of the letter.
     */
    private int getIndexOf(char letter) {
        if (length == 0) {
            return -1;
        }
        // since addSequence will be maintaining sorted order
        // we can take advantage of binary search.
        int low = 0;
        int high = length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            char midChar = this.charArray[mid];

            if (midChar == letter) {
                return mid;
            } else if (midChar < letter) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Helper function that resizes the array whenever
     * length == allocation size and we need to add more elements.
     */
    private void reSize() {
        // variable to store new size (double the original size).
        int newSize = charArray.length * 2;
        // make new arrays according to the new sizes.
        char[] newCharArray = new char[newSize];
        BinarySequence[] newSeqArray = new BinarySequence[newSize];
        // Copy the orignial elemnts to the new arrays.
        for (int i = 0; i < charArray.length; i++) {
            newCharArray[i] = charArray[i];
            newSeqArray[i] = seqArray[i];
         }
         // replace the old arrays with the new.
         charArray = newCharArray;
         seqArray = newSeqArray;

    }

    /**
     * Shifts all the elements to the right (including the given index).
     * @param idx -- the index (inclusive) you want to start shifting to the right.
     */
    public void shiftRightAt(int idx) {
        for (int i = length - 1; i >= idx; i--) {
            charArray[i + 1] = charArray[i];
            seqArray[i + 1] = seqArray[i];
        }

        // [a,b,d,e,f,g,0,0]
        // shiftRightAt(d) [a,b,d,d,e,f,g,0]

    }

    /**
     * Checks if all the characters in a string are a part of the codebook.
     * @param letters -- a String of chars.
     * @return -- true if all letters are in the codebook, false if not.
     */
    public boolean containsAll(String letters) {
        int totalContained = 0;
        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            if (contains(c)) {
                totalContained++;
            }
        }

        if (totalContained == letters.length()) {
            return true;
        }
        return false;
    }

    /**
     * Gets the characters corresponding binary sequence.
     * @param c -- the character whose sequence you want.
     * @return -- Binarysequence of the character given.
     */
    public BinarySequence getSequence(char c) {
        if (getIndexOf(c) == -1) {
            return null;
        }
        return seqArray[getIndexOf(c)];
    }
    /**
     * For each char in the string, get the sequence and append
     * it to one sequence to get a single sequence for the entire
     * string. Encode means from string/characters to binary.
     * @param s -- String in which you want to encode.
     * @return -- Binarysequence of the String.
     */
    public BinarySequence encode(String s) {
        BinarySequence output = new BinarySequence();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            output.append(getSequence(c));
        }

        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                output += charArray[i];
            } else {
            output += charArray[i] + ", ";
            }
        }
        return "[" + output + "]";
    }

    /**
     * Creates a copy of charArray to maintain encapsulation,
     * abstraction, so other classes can loop without modifying
     * our charArray.
     * @return -- char[] a copy of the chararray for looping purposes
     * for outside classes.
     */
    public char[] getAllCharacters() {
        char[] characters = new char[length];

        for (int i = 0; i < length; i++) {
            characters[i] = charArray[i];
        }

        return characters;
    }
}


