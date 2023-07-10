package com.challenge.capitole.capitolechallenge.exception;

import com.challenge.capitole.capitolechallenge.dtos.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleException(final Exception ex) {
        return logAndRespond(ex);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleValidationException(final Exception ex) {
        return logAndRespond(ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleNotFoundException(final Exception ex) {
        return logAndRespond(ex);
    }

    private ErrorDetails logAndRespond(final Exception ex) {
        log.error(ex.getLocalizedMessage(), ex);
        return ErrorDetails.builder().title(ex.getLocalizedMessage()).detail(ex.toString()).build();
    }
}
