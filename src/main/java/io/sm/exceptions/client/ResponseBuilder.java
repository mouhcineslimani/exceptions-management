package io.sm.exceptions.client;

import io.sm.exceptions.dtos.ApiResponse;
import org.springframework.http.HttpStatus;

public class ResponseBuilder {

    public static <T> ApiResponse<T> buildSuccessResponse(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setStatus("success");
        response.setMsg("Operation successful");
        response.setData(data);
        response.setStatusCode(HttpStatus.OK.value());
        return response;
    }

    public static ApiResponse<Object> buildErrorResponse(String message, HttpStatus status) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setStatus("error");
        response.setMsg(message);
        response.setStatusCode(status.value());
        return response;
    }
}
