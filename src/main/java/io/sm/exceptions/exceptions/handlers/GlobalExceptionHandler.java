package io.sm.exceptions.exceptions.handlers;

import feign.FeignException;
import feign.RetryableException;
import feign.codec.DecodeException;
import feign.codec.EncodeException;
import io.sm.exceptions.api.ResponseBuilder;
import io.sm.exceptions.dtos.ApiResponse;
import io.sm.exceptions.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.net.ssl.SSLException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadResquestException(BadRequestException ex) {
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Object>> handleConflictException(ConflictException ex) {
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ApiResponse<Object>> handleServerErrorException(ServerErrorException ex) {
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PersonNotFound.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(PersonNotFound ex) {
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(HttpClientErrorException.BadRequest ex) {
        ApiResponse<Object> response = ResponseBuilder.buildErrorResponse("Bad Request: " + ex.getResponseBodyAsString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConnectException.class, UnknownHostException.class, SSLException.class})
    public ResponseEntity<ApiResponse<Object>> handleConnectionExceptions(Exception ex) {
        ApiResponse<Object> response = ResponseBuilder.buildErrorResponse("Connection failed: " + ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiResponse<Object>> handleDatabaseException(SQLException ex) {
        ApiResponse<Object> response = ResponseBuilder.buildErrorResponse("Database error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllUncaughtExceptions(Exception ex, WebRequest request) {
        log.error("An unexpected error occurred", ex);
        ApiResponse<Object> response = ResponseBuilder.buildErrorResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignStatusException(FeignException ex) {
        HttpStatus status = HttpStatus.resolve(ex.status());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        log.error("FeignException: status={}, message={}", status, ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Feign client error: " + ex.getMessage(), status), status);
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignBadRequestException(FeignException.BadRequest ex) {
        log.error("Feign BadRequest: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Bad Request from Feign client: " + ex.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignUnauthorizedException(FeignException.Unauthorized ex) {
        log.error("Feign Unauthorized: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Unauthorized: " + ex.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignForbiddenException(FeignException.Forbidden ex) {
        log.error("Feign Forbidden: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Forbidden: " + ex.getMessage(), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignNotFoundException(FeignException.NotFound ex) {
        log.error("Feign NotFound: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignConflictException(FeignException.Conflict ex) {
        log.error("Feign Conflict: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Conflict: " + ex.getMessage(), HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignInternalServerError(FeignException.InternalServerError ex) {
        log.error("Feign InternalServerError: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Internal server error from Feign client: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignServiceUnavailable(FeignException.ServiceUnavailable ex) {
        log.error("Feign ServiceUnavailable: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Service unavailable: " + ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignRetryableException(RetryableException ex) {
        log.error("Feign RetryableException: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Service temporarily unavailable, please try again later.", HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(DecodeException.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignDecodeException(DecodeException ex) {
        log.error("Feign DecodeException: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Failed to decode Feign client response: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EncodeException.class)
    public ResponseEntity<ApiResponse<Object>> handleFeignEncodeException(EncodeException ex) {
        log.error("Feign EncodeException: {}", ex.getMessage());
        return new ResponseEntity<>(ResponseBuilder.buildErrorResponse("Failed to encode Feign client request: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setStatus("error");
        response.setMsg("Validation failed");
        response.setData(ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList()));
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
