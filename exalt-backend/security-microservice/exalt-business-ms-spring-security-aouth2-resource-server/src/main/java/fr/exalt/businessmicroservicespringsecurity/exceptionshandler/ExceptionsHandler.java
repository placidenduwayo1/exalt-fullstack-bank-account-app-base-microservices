package fr.exalt.businessmicroservicespringsecurity.exceptionshandler;

import fr.exalt.businessmicroservicespringsecurity.entities.models.ApiError;
import fr.exalt.businessmicroservicespringsecurity.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> handler(Exception exception){
        ApiError error1 = ApiError.builder()
                .errorCode(HttpStatus.PRECONDITION_FAILED.value())
                .errorType(HttpStatus.PRECONDITION_FAILED.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        ApiError error2 = ApiError.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errorType(HttpStatus.BAD_REQUEST.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        ApiError error3 = ApiError.builder()
                .errorCode(HttpStatus.FORBIDDEN.value())
                .errorType(HttpStatus.FORBIDDEN.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();
        ApiError error4 = ApiError.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorType(HttpStatus.NOT_FOUND.name())
                .timestamp(Timestamp.from(Instant.now()))
                .build();

        switch (exception) {
            case UserInformationInvalidException e -> {
                error2.setMessage(e.getMessage());
                return new ResponseEntity<>(error2, HttpStatus.BAD_REQUEST);
            }
            case RoleInformationInvalidException e ->{
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);

            }
            case UserNotFoundException e -> {
                error4.setMessage(e.getMessage());
                return new ResponseEntity<>(error4, HttpStatus.NOT_FOUND);
            }

            case RoleNotFoundException e -> {
                error4.setMessage(e.getMessage());
                return new ResponseEntity<>(error4, HttpStatus.NOT_FOUND);
            }
            case UserAlreadyExistsException e -> {
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
            case RoleAlreadyExistsException e -> {
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
            case PasswordsNotMatchException  e-> {
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
            case UserPossessThisRoleException e -> {
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
            case RoleNoAssignedTheUserException e -> {
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
            case UserAuthenticationFailedException e -> {
                error3.setMessage(e.getMessage());
                return new ResponseEntity<>(error3, HttpStatus.PRECONDITION_FAILED);
            }
            case RefreshTokenMissException e -> {
                error1.setMessage(e.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
            default -> {
                error1.setMessage(exception.getMessage());
                return new ResponseEntity<>(error1, HttpStatus.PRECONDITION_FAILED);
            }
        }
    }
}
