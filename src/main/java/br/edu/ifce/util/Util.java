package br.edu.ifce.util;

public class Util {

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
