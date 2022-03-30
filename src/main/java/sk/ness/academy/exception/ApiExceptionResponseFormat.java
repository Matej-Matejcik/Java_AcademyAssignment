package sk.ness.academy.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApiExceptionResponseFormat {

    private final String message;
    private final HttpStatus httpStatus;
    private final String timeStamp;

    public ApiExceptionResponseFormat(String message, HttpStatus httpStatus, String timeStamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
