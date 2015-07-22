package br.edu.ifce.lz77;

import br.edu.ifce.util.Util;

import java.util.ArrayList;
import java.util.List;

public class LZ77 {

    private int windowSize;

    public LZ77(int windowSize) {
        this.windowSize = windowSize;
    }

    public List<Pointer> encode(String data) {
        List<Pointer> result = new ArrayList<>();
        int position = 0;

        while (position < data.length()) {
            int buffer_size = this.windowSize;
            int window_end = position;
            int window_begin = position - this.windowSize;
            int buffer_end = position + buffer_size;
            int buffer_begin = position;
            int buffer_ignore = (buffer_end > data.length()) ? buffer_end - data.length() : 0;
            int window_position = -1;

            String window = Util.getContent(data, window_begin, window_end);
            String buffer = Util.getContent(data, buffer_begin, buffer_end);
            String match = String.valueOf(buffer.charAt(0));

            while (buffer_size > 0) {
                window_position = window.indexOf(buffer);

                if (window_position != -1) {
                    match = "";
                    break;
                }

                buffer_size--;
                buffer = Util.getContent(data, buffer_begin, position + buffer_size);
            }

            result.add(new Pointer(match, window_position, (buffer_size - buffer_ignore)));
            position = position + ((buffer_size == 0) ? 1 : buffer_size);
        }

        return result;
    }

    public String decode(List<Pointer> pointers) {
        StringBuilder result = new StringBuilder();

        int window_begin = -this.windowSize;
        int window_end = 0;

        for (Pointer p : pointers) {
            String window = Util.getContent(result.toString(), window_begin, window_end);

            if (p.position >= 0) {
                int aux = (window_end - window.length()) + p.position;

                for (int i = 0; i < p.length; i++) {
                    char c = result.charAt(aux + i);
                    result.append(c);
                }

                window_begin += p.length;
            } else {
                result.append(p.value);
                window_begin++;
            }

            window_end = window_begin + this.windowSize;
        }

        return result.toString();
    }
}