package com.freedom.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.freedom.generator.entity.GeneratorTemplate;
import com.freedom.generator.filter.GeneratorTemplateFilter;
import com.freedom.generator.service.GeneratorTemplateService;
import com.freedom.workflow.common.api.CommonResult;
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
    public CommonResult<GeneratorTemplate> findById(Long id) {
        return CommonResult.success(generatorTemplateService.findById(id));
    }

    @RequestMapping("/findByPage")
    public CommonResult<IPage<GeneratorTemplate>> findByPage(@RequestBody GeneratorTemplateFilter filter) {
        return CommonResult.success(generatorTemplateService.findByPage(filter));
    }

    @RequestMapping("/findByList")
    public CommonResult<List<GeneratorTemplate>> findByList(@RequestBody GeneratorTemplateFilter filter) {
        return CommonResult.success(generatorTemplateService.findByList(filter));
    }

    @RequestMapping("/findByTemplateName")
    public CommonResult<List<GeneratorTemplate>> findByTemplateName(@RequestBody GeneratorTemplateFilter filter) {
        return CommonResult.success(generatorTemplateService.findByList(filter));
    }

    @RequestMapping("/saveOrUpdate")
    public CommonResult saveOrUpdate(@RequestBody GeneratorTemplate entity) {
        generatorTemplateService.saveOrUpdate(entity);
        return CommonResult.success(null);
    }

    @RequestMapping("/deleteByIds")
    public CommonResult deleteByIds(@RequestBody Long[] ids) {
        generatorTemplateService.deleteByIds(Arrays.asList(ids));
        return CommonResult.success(null);
    }
}
