package com.freedom.generator.filter;

import lombok.Data;

/**
 * @author freedom
 */
@Data
public class GeneratorTemplateFilter {

    private int start = 0;
    private int limit = 10;
    private String blur;

    private String templateName;
}
