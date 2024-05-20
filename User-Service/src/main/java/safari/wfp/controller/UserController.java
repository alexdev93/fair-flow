package safari.wfp.controller;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import safari.wfp.dto.LoginDTO;
import safari.wfp.model.UserEntity;
import safari.wfp.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/{userName}")
    public List<UserRepresentation> getUser(@PathVariable("userName") String userName) {
        return userService.getUser(userName);
    }

    @PutMapping(path = "/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestBody UserEntity userDTO) {
        userService.updateUser(userId, userDTO);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @GetMapping(path = "/verification-link/{userId}")
    public String sendVerificationLink(@PathVariable("userId") String userId) {
        userService.sendVerificationLink(userId);
        return "Verification Link Send to Registered E-mail Id.";
    }

    @GetMapping(path = "/reset-password/{userId}")
    public String sendResetPassword(@PathVariable("userId") String userId) {
        userService.sendResetPassword(userId);
        return "Reset Password Link Send Successfully to Registered E-mail Id.";
    }

}
