package ecoton.ecotonbackend.exceptions;

import org.springframework.http.HttpStatus;

public class OrganizerNotExistException extends BaseException {
    @Override
    public String getMessage() {
        return "Organizer does not exist";
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
