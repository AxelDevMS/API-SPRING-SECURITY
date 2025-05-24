package com.ams.dev.api.spring.security.exception;

import com.ams.dev.api.spring.security.dto.ApiErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

///sirve para controlar errores
@RestControllerAdvice
public class GlobalException {

     //maneja lo errores
    @ExceptionHandler(Exception.class)
     public ResponseEntity<?> handlerGenericException(HttpServletRequest resquet, Exception exception){
        ApiErrorDto apiErrorDto = new ApiErrorDto();
        apiErrorDto.setBackendMessage(exception.getLocalizedMessage());
        apiErrorDto.setUrl(resquet.getRequestURL().toString());
        apiErrorDto.setMethod(resquet.getMethod());
        apiErrorDto.setTimestamp(LocalDateTime.now());
        apiErrorDto.setMessage("Error interno en el servidor, vuelva a intentarlo");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorDto);
    }


     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest resquet, MethodArgumentNotValidException exception){
         ApiErrorDto apiErrorDto = new ApiErrorDto();
         apiErrorDto.setBackendMessage(exception.getLocalizedMessage());
         apiErrorDto.setUrl(resquet.getRequestURL().toString());
         apiErrorDto.setMethod(resquet.getMethod());
         apiErrorDto.setMessage("Error en la petición enviada");

         System.out.println(exception.getAllErrors().stream().map(each -> each.getDefaultMessage()));

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorDto);
     }



}
