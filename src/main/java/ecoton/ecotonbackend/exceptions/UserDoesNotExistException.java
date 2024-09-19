package ecoton.ecotonbackend.exceptions;

import org.springframework.http.HttpStatus;

public class UserDoesNotExistException extends BaseException {
    @Override
    public String getMessage() {
        return "User does not exist";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
