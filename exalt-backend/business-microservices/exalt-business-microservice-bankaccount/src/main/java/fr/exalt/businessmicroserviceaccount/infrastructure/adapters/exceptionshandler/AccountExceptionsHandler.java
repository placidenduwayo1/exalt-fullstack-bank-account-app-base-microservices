package fr.exalt.businessmicroserviceaccount.infrastructure.adapters.exceptionshandler;

import fr.exalt.businessmicroserviceaccount.domain.exceptions.*;
import fr.exalt.businessmicroserviceaccount.infrastructure.adapters.output.models.entities.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class AccountExceptionsHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handleBusinessExceptions(Exception exception) {
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
                .errorCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .errorType(HttpStatus.SERVICE_UNAVAILABLE.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        ApiError apiError4 = ApiError.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorType(HttpStatus.NOT_FOUND.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();


        switch (exception) {
            case BankAccountBalanceInvalidException e -> {
                apiError1.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }
            case BankAccountFieldsInvalidException e -> {
                apiError2.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError2, HttpStatus.BAD_REQUEST);
            }
            case BankAccountTypeInvalidException e ->{
                apiError1.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }
            case BankAccountStateInvalidException e ->{
                apiError1.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }

           case RemoteCustomerApiUnreachableException e ->{
                apiError3.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError3, HttpStatus.SERVICE_UNAVAILABLE);
           }
           case RemoteCustomerStateInvalidException e ->{
                apiError1.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
           }
            case BankAccountNotFoundException e ->{
                apiError4.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError4, HttpStatus.NOT_FOUND);
            }
            case BankAccountSameStateException e ->{
                apiError1.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }

           case BankAccountTypeNotAcceptedException e ->{
               apiError1.setMessage(e.getMessage());
               return  new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
           }
            case BankAccountOverdraftInvalidException e ->{
                apiError1.setMessage(e.getMessage());
                return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }
            case BankAccountSuspendException e ->{
                apiError1.setMessage(e.getMessage());
               return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }

            default ->{
                apiError1.setMessage(exception.getMessage());
                return new ResponseEntity<>(apiError1, HttpStatus.PRECONDITION_FAILED);
            }
        }
    }
}
