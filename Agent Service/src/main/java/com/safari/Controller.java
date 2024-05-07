package com.safari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/agents")
public class Controller {

    @Autowired
    private WebClient.Builder webClientBuilder;
    @GetMapping(path = "/get")
    public String welcome(){
        return "Hello agent service";
    }

    @GetMapping(path = "/message")
    public String getMessage() {
        try {
            Mono<String> responseData = webClientBuilder.baseUrl("http://Product-Service").build().get()
                    .uri("/api/products/get")
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(response -> response )
                    .defaultIfEmpty("some error");
            return responseData.block() + " within agent service";

        } catch (WebClientException ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    }



}
