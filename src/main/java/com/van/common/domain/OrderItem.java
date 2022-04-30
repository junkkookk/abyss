package com.van.common.domain;

import lombok.Data;

/**
 * @author wan
 */
@Data
public class OrderItem {
    private String column;
    private boolean asc = true;
}
