package sample.web.rest;

//import  org.springframework.http.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 3522415114497042079L;

	public ResourceNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}

	public ResourceNotFoundException(String msg) {
		super(msg, null);
	}
}
