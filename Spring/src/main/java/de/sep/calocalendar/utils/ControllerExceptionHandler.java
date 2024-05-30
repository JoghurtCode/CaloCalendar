package de.sep.calocalendar.utils;

import de.sep.calocalendar.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createAndLogError(ex.getMessage()));
    }

    private static java.lang.Error createAndLogError(String errorMessage) {
        return Optional.ofNullable(errorMessage)
                .map(erMessage -> createAndLogError(List.of(erMessage)))
                .orElseGet(() -> createAndLogError());
    }

    private static java.lang.Error createAndLogError(List<String> errorMessages) {
        return new java.lang.Error()
                .errorMessages(errorMessages)
                .status(((HttpStatus) HttpStatus.BAD_REQUEST).getReasonPhrase());
    }

    private static Error createAndLogError() {
        return new Error()
                .status(((HttpStatus) HttpStatus.BAD_REQUEST).getReasonPhrase());
    }


}
