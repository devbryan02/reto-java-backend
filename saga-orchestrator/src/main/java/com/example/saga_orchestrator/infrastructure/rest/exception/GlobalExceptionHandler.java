package com.example.saga_orchestrator.infrastructure.rest.exception;

import com.example.saga_orchestrator.domain.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBusinessException(
            BusinessException ex,
            ServerWebExchange exchange) {

        String traceId = getTraceId(exchange);

        log.error("[{}] Business Exception [{}]: {}",
                traceId, ex.getClass().getSimpleName(), ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatus().value(),
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                traceId
        );

        return Mono.just(ResponseEntity.status(ex.getStatus()).body(response));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleWebClientException(
            WebClientResponseException ex,
            ServerWebExchange exchange) {

        String traceId = getTraceId(exchange);

        log.error("[{}] Error llamando a microservicio downstream [{}]: {}",
                traceId, ex.getStatusCode(), ex.getResponseBodyAsString());

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                "DOWNSTREAM_SERVICE_ERROR",
                "Error al comunicarse con un microservicio: " + ex.getStatusCode(),
                traceId
        );

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleValidationException(
            WebExchangeBindException ex,
            ServerWebExchange exchange) {

        String traceId = getTraceId(exchange);

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> field.getField() + ": " + field.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.warn("[{}] Validation error: {}", traceId, message);

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                message,
                traceId
        );

        return Mono.just(ResponseEntity.badRequest().body(response));
    }

    @ExceptionHandler(DecodingException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDecodingException(
            DecodingException ex,
            ServerWebExchange exchange) {

        String traceId = getTraceId(exchange);

        log.warn("[{}] Invalid JSON: {}", traceId, ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_REQUEST_BODY",
                "Request body is malformed.",
                traceId
        );

        return Mono.just(ResponseEntity.badRequest().body(response));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleException(
            Exception ex,
            ServerWebExchange exchange) {

        String traceId = getTraceId(exchange);

        log.error("[{}] Unexpected error", traceId, ex);

        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred.",
                traceId
        );

        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
    }

    private String getTraceId(ServerWebExchange exchange) {
        String traceId = exchange.getRequest().getHeaders().getFirst("X-Trace-Id");
        return traceId != null ? traceId : "N/A";
    }
}