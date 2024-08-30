package io.sm.exceptions.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.sm.exceptions.exceptions.BadRequestException;
import io.sm.exceptions.exceptions.ConflictException;
import io.sm.exceptions.exceptions.NotFoundException;
import io.sm.exceptions.exceptions.ServerErrorException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    public static class CustomErrorDecoder implements ErrorDecoder {
        private final ErrorDecoder defaultDecoder = new Default();
        @Override
        public Exception decode(String methodKey, Response response) {
            return switch (response.status()) {
                case 400 ->
                        new BadRequestException("Bad Request: The server could not understand the request due to invalid syntax.");
                case 404 -> new NotFoundException("Resource not found: The requested resource could not be found.");
                case 409 ->
                        new ConflictException("Conflict: The request could not be completed due to a conflict with the current state of the resource.");
                case 500 ->
                        new ServerErrorException("Internal Server Error: The server encountered an unexpected condition that prevented it from fulfilling the request.");
                default -> defaultDecoder.decode(methodKey, response);
            };
        }
    }
}
