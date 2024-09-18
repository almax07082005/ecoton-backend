package ecoton.ecotonbackend.advice;

import ecoton.ecotonbackend.exceptions.BaseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            BaseException.class
    })
    protected ResponseEntity<Object> handleCustomConflict(BaseException exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                ResponseBody.builder()
                        .exceptionName(exception.getClass().getName())
                        .detail(exception.getMessage())
                        .stackTrace(Arrays.toString(exception.getStackTrace()))
                        .build(),
                new HttpHeaders(),
                exception.getStatus(),
                request
        );
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    protected ResponseEntity<Object> handleConflict(Exception exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                ResponseBody.builder()
                        .exceptionName(exception.getClass().getName())
                        .detail(exception.getMessage())
                        .stackTrace(Arrays.toString(exception.getStackTrace()))
                        .build(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
