package com.freedom.generator.controller;

import com.freedom.generator.entity.GeneratorTemplate;
import com.freedom.generator.filter.GeneratorTemplateFilter;
import com.freedom.generator.service.GeneratorTemplateService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author freedom
 */
@RequestMapping("/api/generator")
@RestController
public class GeneratorTemplateController {

    @Resource
    private GeneratorTemplateService generatorTemplateService;

    @RequestMapping("/findById")
    public GeneratorTemplate findById(Long id) {
        return generatorTemplateService.findById(id);
    }

    @RequestMapping("/findByPage")
    public Page<GeneratorTemplate> findByPage(@RequestBody GeneratorTemplateFilter filter) {
        return generatorTemplateService.findByPage(filter);
    }

    @RequestMapping("/findByList")
    public List<GeneratorTemplate> findByList(@RequestBody GeneratorTemplateFilter filter) {
        return generatorTemplateService.findByList(filter);
    }

    @RequestMapping("/findByTemplateName")
    public List<GeneratorTemplate> findByTemplateName(@RequestBody GeneratorTemplateFilter filter) {
        return generatorTemplateService.findByList(filter);
    }

    @RequestMapping("/saveOrUpdate")
    public void saveOrUpdate(@RequestBody GeneratorTemplate entity) {
        generatorTemplateService.saveOrUpdate(entity);
    }

    @RequestMapping("/deleteByIds")
    public void deleteByIds(@RequestBody Long[] ids) {
        generatorTemplateService.deleteByIds(Arrays.asList(ids));
    }
}
