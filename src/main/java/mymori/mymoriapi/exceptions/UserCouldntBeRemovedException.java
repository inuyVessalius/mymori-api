package mymori.mymoriapi.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User couldn't be removed!")  // 404
public class UserCouldntBeRemovedException extends RuntimeException {
    private static final long serialVersionUID = 3;
}
