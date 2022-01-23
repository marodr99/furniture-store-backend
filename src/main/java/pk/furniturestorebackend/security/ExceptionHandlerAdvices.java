package pk.furniturestorebackend.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pk.furniturestorebackend.exceptions.ErrorMessage;
import pk.furniturestorebackend.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvices extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(400, exception.getMessage());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<?> handleNotFoundException(NotFoundException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(404, exception.getMessage());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
