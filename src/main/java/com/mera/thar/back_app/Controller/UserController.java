package com.mera.thar.back_app.Controller;

import com.mera.thar.back_app.Service.UserService;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("api/mera_thar/user/")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping("login")
    private ResponseEntity<?> login(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.login() : {}");
            return new ResponseEntity<>(this.userService.userSignIn(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("register")
    private ResponseEntity<?> register(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.register() : {}");
            return new ResponseEntity<>(this.userService.userSignUp(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("edit")
    private ResponseEntity<?> edit(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.edit() : {}");
            return new ResponseEntity<>(this.userService.userEdit(request), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("email")
    private ResponseEntity<?> findByEmail(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.findByEmail() : {}");
            return new ResponseEntity<>(this.userService.findByEmail(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("list")
    private ResponseEntity<?> list() {
        try {
            logger.info("in UserController.list() : {}");
            return new ResponseEntity<>(this.userService.list(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    private ResponseEntity<?> deleteByEmail(@RequestBody Map<String, Object> request) {
        try {
            logger.info("in UserController.deleteByEmail() : {}");
            return new ResponseEntity<>(this.userService.deleteByEmail(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
