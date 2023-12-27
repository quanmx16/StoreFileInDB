package com.example.uploadfileandstoretodb.exception;

import com.example.uploadfileandstoretodb.viewmodel.ErrorVm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorVm> handleNotFoundException(NotFoundException ex, WebRequest request) {
        String message = ex.getMessage();
        ErrorVm errorVm = new ErrorVm(HttpStatus.NOT_FOUND.toString(), "NotFound", message);
        return new ResponseEntity<>(errorVm, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorVm> handleIOException(IOException ex, WebRequest request) {
        String message = ex.getMessage();
        ErrorVm errorVm = new ErrorVm(HttpStatus.BAD_REQUEST.toString(), "FileStoreException", message);
        return new ResponseEntity<>(errorVm, HttpStatus.BAD_REQUEST);
    }
}
