package mymori.mymoriapi.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User with given name not found!")  // 404
public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -9171449022927898754L;
}
