package io.sm.exceptions.api;

import io.sm.exceptions.dtos.ApiResponse;
import lombok.Builder;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    public static <T> ApiResponse<T> buildSuccessResponse(T data, String msg, HttpStatus status) {
        return ApiResponse.<T>builder()
                .msg(msg)
                .data(data)
                .status("success")
                .statusCode(status.value())
                .build();
    }

    public static <T> ApiResponse<T> buildErrorResponse(String msg, HttpStatus status) {
        return ApiResponse.<T>builder()
                .data(null)
                .status("error")
                .msg(msg)
                .statusCode(status.value())
                .build();
    }
}