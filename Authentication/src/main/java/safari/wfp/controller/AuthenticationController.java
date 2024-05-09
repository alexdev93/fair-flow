package safari.wfp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import safari.wfp.dto.LoginDTO;
import safari.wfp.service.AuthenticationService;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @ResponseBody
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        return authenticationService.signIn(loginDto);
    }
}
