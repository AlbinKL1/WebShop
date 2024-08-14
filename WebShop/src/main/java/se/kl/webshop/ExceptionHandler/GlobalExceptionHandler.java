package se.kl.webshop.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if (ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            message = "Required request parameter is missing.";
        }

        ErrorMessage errorMessage = new ErrorMessage(message, status.value());
        return new ResponseEntity<>(errorMessage, status);
    }
}
