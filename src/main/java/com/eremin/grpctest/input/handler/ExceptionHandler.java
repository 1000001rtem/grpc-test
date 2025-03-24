package com.eremin.grpctest.input.handler;

import com.eremin.grpctest.business.exception.ClothesNotFoundException;
import io.grpc.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;

@Configuration
public class ExceptionHandler {

    @Bean
    public GrpcExceptionHandler grpcExceptionHandler() {
        return (Throwable exception) -> {
            switch (exception) {
                case ClothesNotFoundException e -> {
                    return Status.NOT_FOUND.withDescription(e.getMessage());
                }
                case Exception e -> {
                    return Status.UNKNOWN.withDescription(e.getMessage());
                }
                default -> {
                    return null;
                }
            }
        };
    }
}
