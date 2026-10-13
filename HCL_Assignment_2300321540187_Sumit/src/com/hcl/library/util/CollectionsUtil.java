package com.hcl.library.util;

import com.hcl.library.model.LibraryItem;
import java.util.List;

public final class CollectionsUtil {

    private CollectionsUtil() {
        // utility class: nobody should create an object of it
    }


    public static <T extends Comparable<? super T>> T maxOf(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not be empty");
        }
        T max = list.get(0);
        for (T element : list) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static double totalFees(List<? extends LibraryItem> items, int daysLate) {
        double total = 0;
        for (LibraryItem item : items) {
            total += item.lateFee(daysLate);
        }
        return total;
    }
}