package br.edu.ifce.lz77;

/**
 * Reference:
 * https://msdn.microsoft.com/en-us/library/ee916854.aspx
 */
public class LZ77 {

    private final static String DATA = "aababaaabba";
    private final static int WINDOW_SIZE = 3;

    public static void main(String[] args) {
        int position = 0;

        while (position < DATA.length()) {
            int buffer_size = WINDOW_SIZE;

            int window_end = position;
            int window_begin = position - WINDOW_SIZE;
            int buffer_end = position + buffer_size;
            int buffer_begin = position;

            String window = getContent(window_begin, window_end);
            String buffer = getContent(buffer_begin, buffer_end);

            System.out.println("\n\n" + position);
            System.out.println("window (start = " + window_begin + ", end = " + window_end + ") " + window);
            System.out.println("buffer (start = " + buffer_begin + ", end = " + buffer_end + ") " + buffer);

            String match = String.valueOf(buffer.charAt(0));
            int window_length = window.length();

            while (buffer_size > 0) {

                if (buffer.equals(window)) {
                    System.out.println("found... " + buffer);
                    match = "";
                    break;
                }

                buffer_size--;
                window_length--;
                buffer = getContent(buffer_begin, position + buffer_size);
                window = getContent(position-window_length,window_end);
                System.out.print("Reducing window and buffer...  " + buffer);
                System.out.print(" (window = " + window);
                System.out.println(", buffer = " + buffer +")");
            }

            System.out.println("(" + buffer_size + " ," + position + ") " + match);

            position++;
        }
    }

    public static String getContent(int begin, int end) {
        String result = "";
        char[] chars = DATA.toCharArray();

        for (int i = begin; i < end; i++) {
            if (i >= 0 && i < chars.length) {
                result += chars[i];
            }
        }

        return result;
    }
}

class Pointer {
    public char value;
    public int offset;
    public int length;

    @Override
    public String toString() {
        return value + "(" + offset + "," + length + ")";
    }
}