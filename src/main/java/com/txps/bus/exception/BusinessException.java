package com.txps.bus.exception;

import lombok.Data;

/**
 *
 * @author Wayne
 * @since 2021-09-03
 */
@Data
public class BusinessException extends RuntimeException{

    private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}
