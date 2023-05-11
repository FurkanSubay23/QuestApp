package com.questapp.controlleradvice;

import com.questapp.exception.ErrorHandling;
import com.questapp.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ErrorHandling exc){             // INTEGER değerler için not found handler method.
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage("Object Not Found !!!");
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleExceptionAll(Exception exc){         // String değerler içinde bad request handler method.
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
