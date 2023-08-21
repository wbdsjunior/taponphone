package io.github.wbdsjunior.taponphone.markets;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import feign.RetryableException;

@RestControllerAdvice
public class TaponphoneMarketsApplicationRestControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handleNoHandlerFoundException(final NoHandlerFoundException ex) {

        return ex.getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(final IllegalArgumentException ex) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
                , ex.getMessage()
            );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(final DataIntegrityViolationException ex) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value())
                , ex.getMessage()
            );
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalStateException.class)
    public ProblemDetail handleIllegalStateException(final IllegalStateException ex) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value())
                , ex.getMessage()
            );
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException.class)
    public ProblemDetail handleUnsupportedOperationException(final UnsupportedOperationException ex) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.NOT_IMPLEMENTED.value())
                , ex.getMessage()
            );
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(RetryableException.class)
    public ProblemDetail handleRetryableException(final RetryableException ex) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.BAD_GATEWAY.value())
                , ex.getMessage()
            );
    }
}
