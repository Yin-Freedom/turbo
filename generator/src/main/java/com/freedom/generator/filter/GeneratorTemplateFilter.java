package com.freedom.generator.filter;

/**
 * @author freedom
 */
public class GeneratorTemplateFilter {

    private int start = 0;
    private int limit = 10;
    private String blur;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getBlur() {
        return blur;
    }

    public void setBlur(String blur) {
        this.blur = blur;
    }
}
