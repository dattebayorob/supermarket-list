package io.github.dattebayorob.supermarketlist.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessException extends RuntimeException {
    private List<Error> errors;
    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(String message, Exception reason) {
        super(message, reason);
    }
    public BusinessException(String message, Error ... errors) {
        super(message);
        this.errors = Arrays.asList(errors);
    }
    public List<Error> getErrors() {
        if ( errors == null ) {
            errors = new ArrayList<>();
        }
        return errors;
    }
}
