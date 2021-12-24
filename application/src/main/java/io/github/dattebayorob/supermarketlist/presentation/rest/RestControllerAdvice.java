package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ErrorCode;
import io.github.dattebayorob.supermarketlist.exception.ResourceNotFoundException;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ErrorRepresentation;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ErrorRepresentationErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorRepresentation> handleBusinessException(BusinessException exception) {
        ErrorRepresentation error = new ErrorRepresentation()
                .code(exception.getCode())
                .message(exception.getMessage())
                .errors(toErrorRepresentation(exception.getErrors()));
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorRepresentation> handleThrowable(Throwable exception) {
        ErrorRepresentation error = new ErrorRepresentation().code(ErrorCode.INTERNAL_SERVER_ERROR).message(exception.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    private List<ErrorRepresentationErrors> toErrorRepresentation(List<BusinessException.Error> errors) {
        return errors.stream().map(domainErrorToErrorRepresentation()).collect(Collectors.toList());
    }

    private Function<BusinessException.Error, ErrorRepresentationErrors> domainErrorToErrorRepresentation() {
        return error -> new ErrorRepresentationErrors().field(error.getField()).message(error.getMessage());
    }

}
