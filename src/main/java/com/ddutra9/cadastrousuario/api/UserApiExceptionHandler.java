package com.ddutra9.cadastrousuario.api;

import com.ddutra9.cadastrousuario.domain.exception.BusinessException;
import com.ddutra9.cadastrousuario.domain.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class UserApiExceptionHandler  extends ResponseEntityExceptionHandler {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity exceptions(NotFoundException exception) {
        this.logger.error(exception.getMessage(), exception);
        ResponseStatusError error = new ResponseStatusError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity exceptions(BusinessException exception) {
        this.logger.error(exception.getMessage(), exception);
        ResponseStatusError error = new ResponseStatusError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity exceptions(ConstraintViolationException exception) {
        this.logger.error(exception.getMessage(), exception);
        ResponseStatusError error = new ResponseStatusError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
