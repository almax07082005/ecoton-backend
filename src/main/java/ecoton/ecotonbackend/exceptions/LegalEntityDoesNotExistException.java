package ecoton.ecotonbackend.exceptions;

import org.springframework.http.HttpStatus;

public class LegalEntityDoesNotExistException extends BaseException {
	@Override
	public String getMessage() {
		return "Legal entity does not exist";
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
