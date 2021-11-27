package com.fernando.socialmediaservice.handler;

import com.fernando.socialmediaservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerMethodNotValidException(
            MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .title("Bad Request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .details(Objects.requireNonNull(exception.getFieldError()).getDefaultMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .title("Not Found")
                        .status(HttpStatus.NOT_FOUND.value())
                        .details(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ConflictExceptionDetails> handlerConflictException(
            ConflictException exception) {
        return new ResponseEntity<>(
                ConflictExceptionDetails.builder()
                        .title("Conflict")
                        .status(HttpStatus.CONFLICT.value())
                        .details(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PreConditionFailedException.class)
    public ResponseEntity<PreConditionFailedExceptionDetails> handlerPreConditionFailedException(
            PreConditionFailedException exception) {
        return new ResponseEntity<>(
                PreConditionFailedExceptionDetails.builder()
                        .title("Pre-Condition Failed")
                        .status(HttpStatus.PRECONDITION_FAILED.value())
                        .details(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<UnprocessableEntityExceptionDetails> handlerUnprocessableEntityException(
            UnprocessableEntityException exception) {
        return new ResponseEntity<>(
                UnprocessableEntityExceptionDetails.builder()
                        .title("Unprocessable Entity")
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                        .details(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<InternalServerErrorExceptionDetails> handlerInternalServerErrorException(
            InternalServerErrorException exception) {
        return new ResponseEntity<>(
                InternalServerErrorExceptionDetails.builder()
                        .title("Internal Server Error")
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .details(exception.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
