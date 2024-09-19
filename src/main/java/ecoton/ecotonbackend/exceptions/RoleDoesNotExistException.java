package ecoton.ecotonbackend.exceptions;

import org.springframework.http.HttpStatus;

public class RoleDoesNotExistException extends BaseException {
	@Override
	public String getMessage() {
		return "enter valid role: USER, ORGANIZER, OFFICIAL, ADMIN";
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
