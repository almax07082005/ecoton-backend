package ecoton.ecotonbackend.exceptions;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;

@StandardException
public abstract class BaseException extends RuntimeException {
    @Override
    public abstract String getMessage();
    public abstract HttpStatus getStatus();
}
