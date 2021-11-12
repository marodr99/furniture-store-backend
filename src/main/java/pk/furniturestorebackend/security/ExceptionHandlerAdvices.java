package pk.furniturestorebackend.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pk.furniturestorebackend.exceptions.ErrorMessage;
import pk.furniturestorebackend.exceptions.UserAlreadyExistException;

@ControllerAdvice
public class ExceptionHandlerAdvices extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UserAlreadyExistException.class)
    protected ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(404, exception.getMessage());
        return handleExceptionInternal(exception, message, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
