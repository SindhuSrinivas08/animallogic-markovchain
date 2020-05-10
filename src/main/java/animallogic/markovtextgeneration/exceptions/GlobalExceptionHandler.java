package animallogic.markovtextgeneration.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.channels.OverlappingFileLockException;

@ControllerAdvice
public class GlobalExceptionHandler{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExceptions(Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        logger.error("", ex);
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRunTimeExceptions(RuntimeException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        logger.error("", ex);
        if (ex instanceof MultipartException) {
            return new ResponseEntity<Object>(
                    "File size cannot exceed 20MB", headers, HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE);
        } else if (ex instanceof OverlappingFileLockException) {
            return new ResponseEntity<Object>(
                    "File is locked", new HttpHeaders(), HttpStatus.LOCKED);

        } else if (ex instanceof InvalidInputException) {
            return new ResponseEntity<Object>(
                    ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<Object>(
                    ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
