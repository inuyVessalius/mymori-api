package mymori.mymoriapi.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Card(s) couldn't be saved!")  // 404
public class CardCouldntBeSavedException extends RuntimeException {
    private static final long serialVersionUID = 3;
}
