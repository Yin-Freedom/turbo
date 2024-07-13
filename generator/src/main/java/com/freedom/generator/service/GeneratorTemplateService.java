package com.freedom.generator.service;

import com.freedom.generator.dao.GeneratorTemplateDAO;
import com.freedom.generator.entity.GeneratorTemplate;
import com.freedom.generator.filter.GeneratorTemplateFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        return generatorTemplateDAO.findById(id).orElse(null);
    }

    public Page<GeneratorTemplate> findByPage(GeneratorTemplateFilter filter) {
        return generatorTemplateDAO.findByPage(filter);
    }

    public List<GeneratorTemplate> findByList(GeneratorTemplateFilter filter) {
        filter.setStart(0);
        filter.setLimit(Integer.MAX_VALUE);
        Page<GeneratorTemplate> page = findByPage(filter);
        return page.getContent();
    }

    public void saveOrUpdate(GeneratorTemplate entity) {
        GeneratorTemplate db = generatorTemplateDAO.findById(entity.getId()).orElse(null);
        if (db == null) {
            generatorTemplateDAO.save(entity);
        } else {
            BeanUtils.copyProperties(entity, db, "id");
            generatorTemplateDAO.save(db);
        }
    }

    public void deleteByIds(Collection<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        for (Long id : ids) {
            generatorTemplateDAO.deleteById(id);
        }
    }
}
