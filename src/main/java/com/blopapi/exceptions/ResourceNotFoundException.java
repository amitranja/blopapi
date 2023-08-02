package com.blopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException  {
    private long id;

    public ResourceNotFoundException(long id) {
        super("Resource is not found by id:"+id);
    }
}
