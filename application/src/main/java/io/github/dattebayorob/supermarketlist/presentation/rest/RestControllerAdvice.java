package io.github.dattebayorob.supermarketlist.presentation.rest;

import io.github.dattebayorob.supermarketlist.exception.BusinessException;
import io.github.dattebayorob.supermarketlist.exception.ErrorCode;
import io.github.dattebayorob.supermarketlist.exception.ResourceNotFoundException;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ErrorRepresentation;
import io.github.dattebayorob.supermarketlist.presentation.rest.representation.ErrorRepresentationErrors;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorRepresentation> handleConstraintViolationException(ConstraintViolationException exception) {
        ErrorRepresentation error = new ErrorRepresentation()
                .code(ErrorCode.REQUEST_PARAM_ERROR)
                .errors(toErrorRepresentation(exception.getConstraintViolations()));
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorRepresentation> handlePropertyReferenceException(PropertyReferenceException exception) {
        ErrorRepresentation error = new ErrorRepresentation()
                .code(ErrorCode.REQUEST_PARAM_ERROR)
                .errors(List.of(new ErrorRepresentationErrors().field("The parameter "+exception.getPropertyName()+" is invalid.")));
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorRepresentation handleJwtExcepetion(JwtException jwtException) {
        return toErrorRepresentation(jwtException);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorRepresentation handleAuthenticationException(AuthenticationException authenticationException) {
        return toErrorRepresentation(authenticationException);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorRepresentation> handleThrowable(Throwable exception) {
        log.error("{}", exception);
        ErrorRepresentation error = new ErrorRepresentation().code(ErrorCode.INTERNAL_SERVER_ERROR).message(exception.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    private List<ErrorRepresentationErrors> toErrorRepresentation(List<BusinessException.Error> errors) {
        return errors.stream().map(domainErrorToErrorRepresentation()).collect(Collectors.toList());
    }

    private Function<BusinessException.Error, ErrorRepresentationErrors> domainErrorToErrorRepresentation() {
        return error -> new ErrorRepresentationErrors().field(error.getField()).message(error.getMessage());
    }

    private List<ErrorRepresentationErrors> toErrorRepresentation(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream().map(constraintViolationToErrorRepresentation()).collect(Collectors.toList());
    }

    private Function<ConstraintViolation<?>, ErrorRepresentationErrors> constraintViolationToErrorRepresentation() {
        return constraintViolation -> {
            var field = constraintViolation.getPropertyPath().toString();
            field = field.substring(field.indexOf(".")+1);
            var message = constraintViolation.getMessage();
            return new ErrorRepresentationErrors().field(field).message(message);
        };
    }

    private ErrorRepresentation toErrorRepresentation(Exception ex) {
        return new ErrorRepresentation().message(ex.getMessage());
    }

}
