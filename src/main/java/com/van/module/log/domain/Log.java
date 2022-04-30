package com.van.module.log.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wan
 * @since 2022-04-29
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String ip;

    private String url;

    private String httpMethod;

    /**
     * 接口耗时
     */
    private Long timeCost;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * UA
     */
    private String userAgent;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作用户
     */
    private String operator;

    /**
     * 请求参数
     */
    private String param;


}
