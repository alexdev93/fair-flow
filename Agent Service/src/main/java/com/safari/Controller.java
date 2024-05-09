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

    @GetMapping(path = "/get")
    public String welcome(){
        return "Hello agent service";
    }



}
