package fr.exalt.businessmicroservicecustomer.infrastructure.adapters.excetionshandler;

import fr.exalt.businessmicroservicecustomer.domain.exceptions.*;
import fr.exalt.businessmicroservicecustomer.infrastructure.adapters.output.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class CustomerExceptionsHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleBusinessException (Exception exception){

        ApiError apiError1 = ApiError.builder()
                .errorCode(HttpStatus.PRECONDITION_FAILED.value())
                .errorType(HttpStatus.PRECONDITION_FAILED.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        ApiError apiError2 = ApiError.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorType(HttpStatus.BAD_REQUEST.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        ApiError apiError3 = ApiError.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorType(HttpStatus.NOT_FOUND.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        switch (exception) {
           case CustomerNotFoundException e -> {
               apiError3.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError3, HttpStatus.NOT_FOUND);
           }
           case CustomerOneOrMoreFieldsInvalidException e -> {
               apiError2.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.BAD_REQUEST);
           }
           case CustomerStateInvalidException e -> {
               apiError1.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
           }
           case CustomerAlreadyExistsException e -> {
               apiError1.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
           }

           case AddressNotFoundException e -> {
               apiError3.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.NOT_FOUND);
           }
           default ->{
               apiError1.setMessage(exception.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
           }
       }
    }
}
