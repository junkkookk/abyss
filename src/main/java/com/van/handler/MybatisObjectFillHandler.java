package com.van.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wan
 */
@Component
public class MybatisObjectFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("status",1,metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
