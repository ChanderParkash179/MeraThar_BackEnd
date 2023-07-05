package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("api/user/")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("signin")
    private ResponseEntity<?> login(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.userService.userSignIn(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("signup")
    private ResponseEntity<?> register(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<>(this.userService.userSignUp(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
