package safari.wfp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDTO {

    private  String apiPath;

    private HttpStatus errorCode;

    private  String errorMessage;

    private String methodName;
}