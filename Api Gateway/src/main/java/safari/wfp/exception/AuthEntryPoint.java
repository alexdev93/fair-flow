//package safari.wfp.exception;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//import safari.wfp.dto.ErrorResponseDto;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.time.LocalDateTime;
//
//@Component
//public class AuthEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException authException) {
//        ServerHttpResponse response = exchange.getResponse();
//        ServerHttpRequest request = exchange.getRequest();
//
//        // Redirect the user to the login page or return an unauthorized status code, depending on your requirements
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        return response.setComplete();
//    }
//
//    @ExceptionHandler(AuthenticationException.class)
//    public Mono<ResponseEntity<ErrorResponseDto>> handleAuthenticationException(AuthenticationException ex) {
//        ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.UNAUTHORIZED, ex.getMessage());
//        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseDto));
//    }
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//
//    }
//}
