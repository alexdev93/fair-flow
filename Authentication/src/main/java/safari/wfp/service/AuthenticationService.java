package safari.wfp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import safari.wfp.dto.LoginDTO;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final WebClient webClient;

    @Value("${keycloak.client.id}")
    private String clientId;
    @Value("${keycloak.client.secret}")
    private String clientSecret;
    @Value("${keycloak.url}")
    private String issueUri;
    public Mono<ResponseEntity<String>> signIn(LoginDTO loginDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("username", loginDTO.getUsername());
        body.add("password", loginDTO.getPassword());
        body.add("grant_type", "password");

        return webClient
                .post()
                .uri(issueUri)
                .headers(h -> h.addAll(headers))
                .body(BodyInserters.fromFormData(body))
                .retrieve()
                .toEntity(String.class);
    }
}
