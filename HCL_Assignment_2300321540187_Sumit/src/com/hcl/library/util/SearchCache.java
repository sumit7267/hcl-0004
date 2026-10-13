package com.hcl.library.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class SearchCache extends LinkedHashMap<String, Integer> {

    private static final long serialVersionUID = 1L;
    private static final int MAX_ENTRIES = 5;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    public SearchCache() {
        // true = access order: a search that is used again moves to the "newest" end
        super(INITIAL_CAPACITY, LOAD_FACTOR, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
        return size() > MAX_ENTRIES;
    }

    public void recordSearch(String title) {
        put(title, getOrDefault(title, 0) + 1);
    }


    public List<String> recentSearches() {
        return new ArrayList<>(keySet());
    }
}