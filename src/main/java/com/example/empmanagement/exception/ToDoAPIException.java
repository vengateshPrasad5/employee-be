package com.example.empmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ToDoAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
