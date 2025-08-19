package org.liandro;

import java.util.*;

public class CharCount {

    public static void main(String[] args) {

        getCharCount("tomorrow");
        
    }

    public static void getCharCount(String name) {

        Map<Character, Integer> charMap = new HashMap<>();

        char[] strArray = name.toLowerCase().toCharArray();

        for (char c : strArray) {
            if (!String.valueOf(c).isBlank()) {
                if (charMap.containsKey(c)) {
                    charMap.put(c, charMap.get(c) + 1);
                } else {
                    charMap.put(c, 1);
                }
            }
        }

        System.out.println(charMap);

    }

}
