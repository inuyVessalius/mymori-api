package mymori.mymoriapi.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Card already exists!")  // 404
public class CardAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 2;
}
