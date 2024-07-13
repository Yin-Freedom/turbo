package com.freedom.generator.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author freedom
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<?> results = new ArrayList<>(0);
    private long totalCounts;

    public Page() {
    }

    public List<?> getResults() {
        return results;
    }

    public void setResults(List<?> results) {
        this.results = results;
    }

    public long getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(long totalCounts) {
        this.totalCounts = totalCounts;
    }
}
