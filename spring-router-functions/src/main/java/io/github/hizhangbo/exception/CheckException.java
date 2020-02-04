package io.github.hizhangbo.exception;

import lombok.Data;

/**
 * @author Bob
 * @date 2020-02-03 0:32
 */
@Data
public class CheckException extends RuntimeException {

    private String fieldName;

    private String fieldValue;

    public CheckException(String fieldName, String fieldValue) {
        super();
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
