package ecoton.ecotonbackend.exceptions;

import org.springframework.http.HttpStatus;

public class EventNotExistException extends BaseException {
    @Override
    public String getMessage() {
        return "Event does not exist";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
