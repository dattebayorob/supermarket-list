package io.github.dattebayorob.supermarketlist.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BusinessException extends RuntimeException {
    private String code;
    private final List<Error> errors;
    public BusinessException(String message) {
        super(message);
        this.errors = Collections.emptyList();
    }
    public BusinessException(String message, Exception reason) {
        super(message, reason);
        this.errors = Collections.emptyList();
    }
    public BusinessException(String message, Error ... errors) {
        super(message);
        this.errors = Arrays.asList(errors);
    }
    public String getCode() {
        return code;
    }
    public List<Error> getErrors() {
        return errors;
    }
    public BusinessException code(String code) {
        this.code = code;
        return this;
    }
    public BusinessException error(Error error) {
        this.errors.add(error);
        return this;
    }
    @Getter
    @AllArgsConstructor
    public static final class Error{
        private final String field;
        private final String message;
    }
}
