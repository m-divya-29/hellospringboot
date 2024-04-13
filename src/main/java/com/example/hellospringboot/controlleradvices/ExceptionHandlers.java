package com.example.hellospringboot.controlleradvices;

import com.example.hellospringboot.dtos.ExceptionDTO;
import com.example.hellospringboot.exceptions.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    /**
     * Global ProductNotExistsException handler.
     * @param e ProductNotExistsException exception
     * @return ExceptionDTO with message set
     */
    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistsException(ProductNotExistsException e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(e.getMessage());
        //dto.setDetails(e.getCause().toString());
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
