package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Gender;
import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Entity.Social;
import com.mera.thar.back_app.Entity.SystemUtils;
import com.mera.thar.back_app.Model.User;
import com.mera.thar.back_app.Repository.UserRepository;
import com.mera.thar.back_app.Service.UserService;
import com.mera.thar.back_app.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Response userSignUp(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String firstName = (String) input.get("firstName") != null ? (String) input.get("firstName") : null;
        String lastName = (String) input.get("lastName") != null ? (String) input.get("lastName") : null;
        String email = (String) input.get("email") != null ? (String) input.get("email") : null;
        String password = (String) input.get("password") != null ? (String) input.get("password") : null;
        String gender = (String) input.get("gender") != null ? (String) input.get("gender") : null;

        email = email.toLowerCase();
        password = password.toString().trim();
        try {
            if (input == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_INPUT_EMPTY);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_INVALID);
                response.setResponseData(responseData);
                return response;
            } else if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && password.isEmpty() && gender.toString().isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_PARAMETERS_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_UNAVAILABLE);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validateEmail(email)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_EMAIL_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_EMAIL_POLICY);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validatePassword(password)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_PASSWORD_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_PASSWORD_POLICY);
                response.setResponseData(responseData);
                return response;
            } else {
                User user = this.userRepository.findByEmail(email);

                if (user != null) {
                    responseData.put("user", user);
                    response.setResponseCode(AppConstants.USER_ALREADY_EXISTS);
                    response.setResponseMessage(AppConstants.MSG_USER_ALREADY_EXIST);
                    response.setResponseData(responseData);
                    return response;
                } else {
                    String encodedPassword = passwordEncoder.encode(password);

                    Gender genderOfUser = Gender.valueOf(gender);

                    user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setSocial(Social.APPLICATION);
                    user.setGender(genderOfUser);
                    user.setPassword(encodedPassword);

                    this.userRepository.save(user);

                    responseData.put("user", user);
                    response.setResponseCode(AppConstants.USER_CREATED);
                    response.setResponseMessage(AppConstants.MSG_USER_SIGNUP_SUCCESSFULLY);
                    response.setResponseData(responseData);
                    return response;
                }
            }
        } catch (Exception ex) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.USER_SIGNUP_FAILED);
            response.setResponseMessage(AppConstants.MSG_USER_SIGNUP_FAILED);
            response.setResponseData(responseData);
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public Response userSignIn(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = (String) input.get("email") != null ? (String) input.get("email") : null;
        String password = (String) input.get("password") != null ? (String) input.get("password") : null;
        email = email.toLowerCase();
        password = password.toString().trim();

        try {
            if (input == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_INPUT_EMPTY);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_INVALID);
                response.setResponseData(responseData);
                return response;
            } else if (email.isEmpty() && password.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_PARAMETERS_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_UNAVAILABLE);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validateEmail(email)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_EMAIL_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_EMAIL_POLICY);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validatePassword(password)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_PASSWORD_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_PASSWORD_POLICY);
                response.setResponseData(responseData);
                return response;
            } else {
                User user = this.userRepository.findByEmail(email);

                if (user == null) {
                    responseData.put("user", null);
                    response.setResponseCode(AppConstants.USER_NOT_FOUND);
                    response.setResponseMessage(AppConstants.MSG_NO_USER_EXIST);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    boolean matchPassword = passwordEncoder.matches(password, user.getPassword());
                    if (matchPassword) {
                        responseData.put("user", user);
                        response.setResponseCode(AppConstants.USER_LOGIN);
                        response.setResponseMessage(AppConstants.MSG_USER_LOGIN_SUCCESSFULLY);
                        response.setResponseData(responseData);
                        return response;
                    } else {
                        responseData.put("user", null);
                        response.setResponseCode(AppConstants.USER_PASSWORD_INVALID);
                        response.setResponseMessage(AppConstants.MSG_USER_PASSWORD_INCORRECT);
                        response.setResponseData(responseData);
                        return response;
                    }
                }
            }
        } catch (Exception ex) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.USER_LOGIN_FAILED);
            response.setResponseMessage(AppConstants.MSG_USER_LOGIN_FAILED);
            response.setResponseData(responseData);
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response findByEmail(Map<String, Object> input) {
        Map<String, Object> responseData = new HashMap<>();
        Response response = new Response();

        String email = (String) input.get("email") != null ? (String) input.get("email") : null;

        try {
            if (input == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_INPUT_EMPTY);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_INVALID);
                response.setResponseData(responseData);
                return response;
            } else if (email.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_PARAMETERS_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_UNAVAILABLE);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validateEmail(email)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.USER_EMAIL_INVALID);
                response.setResponseMessage(AppConstants.MSG_USER_EMAIL_POLICY);
                response.setResponseData(responseData);
                return response;
            } else {
                User user = this.userRepository.findByEmail(email);

                if (user == null) {
                    responseData.put("user", null);
                    response.setResponseCode(AppConstants.USER_NOT_FOUND);
                    response.setResponseMessage(AppConstants.MSG_NO_USER_EXIST);
                    response.setResponseData(responseData);
                    return response;
                } else {
                    responseData.put("user", user);
                    response.setResponseCode(AppConstants.USER_FOUND);
                    response.setResponseMessage(AppConstants.MSG_USER_FOUND_SUCCESSFULLY);
                    response.setResponseData(responseData);
                    return response;
                }
            }
        } catch (Exception ex) {
            responseData.put("user", null);
            response.setResponseCode(AppConstants.USER_LOGIN_FAILED);
            response.setResponseMessage(AppConstants.MSG_USER_LOGIN_FAILED);
            response.setResponseData(responseData);
            ex.printStackTrace();
        }
        return response;
    }
}