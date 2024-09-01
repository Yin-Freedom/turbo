package com.freedom.generator.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.freedom.generator.dao.GeneratorTemplateDAO;
import com.freedom.generator.entity.GeneratorTemplate;
import com.freedom.generator.filter.GeneratorTemplateFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author freedom
 */
@Service
public class GeneratorTemplateService {

    @Resource
    private GeneratorTemplateDAO generatorTemplateDAO;

    public GeneratorTemplate findById(Long id) {
        return generatorTemplateDAO.getById(id);
    }

    public IPage<GeneratorTemplate> findByPage(GeneratorTemplateFilter filter) {
        IPage<GeneratorTemplate> page = new Page<>(filter.getStart(), filter.getLimit());
        QueryWrapper<GeneratorTemplate> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(filter.getTemplateName())) {
            queryWrapper.like("template_name", filter.getTemplateName());
        }
        queryWrapper.orderByDesc("id");
        return generatorTemplateDAO.page(page, queryWrapper);
    }

    public List<GeneratorTemplate> findByList(GeneratorTemplateFilter filter) {
        QueryWrapper<GeneratorTemplate> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(filter.getTemplateName())) {
            queryWrapper.like("template_name", filter.getTemplateName());
        }
        return generatorTemplateDAO.list(queryWrapper);
    }

    public void saveOrUpdate(GeneratorTemplate entity) {
        generatorTemplateDAO.saveOrUpdate(entity);
    }

    public void deleteByIds(Collection<Long> ids) {
        generatorTemplateDAO.removeByIds(ids);
    }
}
