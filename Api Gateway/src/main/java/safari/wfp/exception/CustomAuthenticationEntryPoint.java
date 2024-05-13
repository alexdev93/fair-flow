package safari.wfp.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import safari.wfp.dto.ErrorResponseDTO;

public class CustomAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();

        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorMessage(e.getMessage())
                .apiPath(String.valueOf(request.getURI()))
                .methodName(String.valueOf(request.getMethod()))
                .errorCode(HttpStatus.UNAUTHORIZED)
                .build();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] responseBytes = new byte[0];
        try {
            responseBytes = objectMapper.writeValueAsBytes(errorResponse);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }

        response.getHeaders().setContentLength(responseBytes.length);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBytes)));
    }
}