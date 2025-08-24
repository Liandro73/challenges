package org.liandro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UncommonValues {

    public static void main(String[] args) {

        Integer[] x = {4, 5, 2, 1};
        Integer[] y = {2, 4, 3, 5};

        List<Integer> w = new ArrayList<>();
        List<Integer> k = new ArrayList<>();
        Collections.addAll(w, x);
        Collections.addAll(w, y);

        for (Integer item : w) {
            if (!k.contains(item)) {
                k.add(item);
            }
        }

        System.out.println(k);

        Collections.sort(k);

        System.out.println(k);

    }

}
