package com.freedom.generator.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yinhuidong
 */
@Data
@TableName("generator_template")
public class GeneratorTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 模板名
     */
    private String templateName;

    /**
     * 模板内容
     */
    private String templateValue;

    /**
     * 创建人
     */
    private String creator;
}



