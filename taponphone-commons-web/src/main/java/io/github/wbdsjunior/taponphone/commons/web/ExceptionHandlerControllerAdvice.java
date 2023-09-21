package io.github.wbdsjunior.taponphone.commons.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handleNoHandlerFoundException(final NoHandlerFoundException e) {

        return e.getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(final IllegalArgumentException e) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST
                        .value())
                , e.getMessage()
            );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        // TODO: remap erros
        return e.getBody();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(final DataIntegrityViolationException e) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST
                        .value())
                , e.getMessage()
            );
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalStateException.class)
    public ProblemDetail handleIllegalStateException(final IllegalStateException e) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.NOT_FOUND
                        .value())
                , e.getMessage()
            );
    }

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException.class)
    public ProblemDetail handleUnsupportedOperationException(final UnsupportedOperationException e) {

        return ProblemDetail.forStatusAndDetail(
                  HttpStatusCode.valueOf(HttpStatus.NOT_IMPLEMENTED
                        .value())
                , e.getMessage()
            );
    }
}
