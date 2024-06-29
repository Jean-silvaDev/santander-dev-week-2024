package me.dio.controller.exception;

import java.util.NoSuchElementException;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ch.qos.logback.classic.Logger;


@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException) {
       return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException) {
       return new ResponseEntity<>("Resource ID not found.", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException) {
       logger.error("Unexpected served error, see the logs.", unexpectedException);
       return new ResponseEntity<>("Unexpected served error, see the logs.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
