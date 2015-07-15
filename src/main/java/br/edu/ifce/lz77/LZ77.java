package br.edu.ifce.lz77;

import java.util.ArrayList;
import java.util.List;

public class LZ77 {

    public static void main(String[] args) {
        new LZ77().encode("abbabbabbbaababa", 4);
    }

    public List<Pointer> encode(String data, int window_size) {
        List<Pointer> result = new ArrayList<>();
        int position = 0;

        while (position < data.length()) {
            int buffer_size = window_size;
            int window_end = position;
            int window_begin = position - window_size;
            int buffer_end = position + buffer_size;
            int buffer_begin = position;
            int buffer_ignore = (buffer_end > data.length()) ? buffer_end - data.length() : 0;
            int window_position = -1;

            String window = getContent(data, window_begin, window_end);
            String buffer = getContent(data, buffer_begin, buffer_end);
            String match = String.valueOf(buffer.charAt(0));

            while (buffer_size > 0) {
                window_position = window.indexOf(buffer);

                if (window_position != -1) {
                    match = "";
                    break;
                }

                buffer_size--;
                buffer = getContent(data, buffer_begin, position + buffer_size);
            }

            result.add(new Pointer(match, window_position, (buffer_size - buffer_ignore)));
            position = position + ((buffer_size == 0) ? 1 : buffer_size);
        }

        return result;
    }

    public static String getContent(String data, int begin, int end) {
        String result = "";
        char[] chars = data.toCharArray();

        for (int i = begin; i < end; i++) {
            if (i >= 0 && i < chars.length) {
                result += chars[i];
            }
        }

        return result;
    }
}