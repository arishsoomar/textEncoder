import provided.BinarySequence;

public class Main {

    public static void shiftRightAt(char[] charArray, int idx, int length) {
        for (int i = length - 1; i >= idx; i--) {
            charArray[i + 1] = charArray[i];
        }
    }

    public static void main(String[] args) {
        // char[] test = new char[12];
        // test[0] = 'a';
        // test[1] = 'b';
        // test[2] = 'd';
        // test[3] = 'e';
        // test[4] = 'f';
        // test[5] = 'g';
        // shiftRightAt(test, 2, 6);
        // System.out.println(test);

        // char c = 'c';
        // int currentIdx = 0;
        // while (c > test[currentIdx]) {
        //     currentIdx++;
        // }

        // test[currentIdx] = c;
        // System.out.println(test);

        // String output = "Hello";

        // for (int i = 0; i < output.length(); i++){
        //     System.out.println(output.charAt(i));
        // }
        // char c = '\0';
        // System.out.println((int) c);
        
    }
}