package com.freedom.generator.dao;

import com.freedom.generator.entity.GeneratorTemplate;
import com.freedom.generator.filter.GeneratorTemplateFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author freedom
 */
public interface GeneratorTemplateDAO extends JpaRepository<GeneratorTemplate, Long> {

    default Page<GeneratorTemplate> findByPage(GeneratorTemplateFilter filter) {
        Pageable pageable = PageRequest.of(filter.getStart(), filter.getLimit(), Sort.by(Sort.Direction.DESC, "id"));
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.exact()).withMatcher("creator", ExampleMatcher.GenericPropertyMatchers.contains());
        GeneratorTemplate exampleObject = new GeneratorTemplate();
        BeanUtils.copyProperties(filter, exampleObject);
        Example<GeneratorTemplate> example = Example.of(exampleObject, matcher);
        return findAll(example, pageable);
    }
}
