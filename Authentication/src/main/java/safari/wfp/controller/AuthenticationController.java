package safari.wfp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import safari.wfp.dto.LoginDTO;
import safari.wfp.service.AuthenticationService;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @ResponseBody
    @PostMapping(path = "/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginDTO loginDto) {
        return authenticationService.signIn(loginDto);
    }
}
