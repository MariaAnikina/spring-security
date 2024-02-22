package maria.anikina.springsecurity.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import maria.anikina.springsecurity.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler({BadCredentialsException.class, UserAlreadyExistsException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@GetMapping()
	public String handleValidationError(Exception e) {
		return e.getMessage();
	}

	@AllArgsConstructor
	@Getter
	@Setter
	public static class ErrorResponse {
		private final String error;
	}
}
