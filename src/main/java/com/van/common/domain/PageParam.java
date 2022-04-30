package com.van.common.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wan
 */
@Data
public class PageParam {

    @NotNull(message = "分页参数不能为空")
    protected Integer current;

    @NotNull(message = "每页数量不能为空")
    @Max(value = 500, message = "每页最大为500")
    protected Integer pageSize;

    protected Boolean searchCount;

    protected List<OrderItem> orders;

}
