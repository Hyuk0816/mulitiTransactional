package dev.study.multitransaction.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EndDateException extends RuntimeException{
    public EndDateException(String message) {
        super(message);
    }
}
