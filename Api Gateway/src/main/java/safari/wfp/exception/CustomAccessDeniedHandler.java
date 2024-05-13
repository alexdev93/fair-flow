package safari.wfp.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import safari.wfp.dto.ErrorResponseDTO;

public class CustomAccessDeniedHandler implements ServerAccessDeniedHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();

        // Create ErrorResponseDTO
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorMessage(denied.getMessage())
                .errorCode(HttpStatus.FORBIDDEN)
                .apiPath(String.valueOf(request.getURI()))
                .methodName(String.valueOf(request.getMethod()))
                .build();

        // Set response status code
        response.setStatusCode(HttpStatus.FORBIDDEN);

        // Serialize ErrorResponseDTO to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] responseBytes = new byte[0];
        try {
            responseBytes = objectMapper.writeValueAsBytes(errorResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Set response body
        response.getHeaders().setContentLength(responseBytes.length);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBytes)));
    }
}
