package com.mera.thar.back_app.Service.Impl;

import com.mera.thar.back_app.Entity.Response;
import com.mera.thar.back_app.Entity.SystemUtils;
import com.mera.thar.back_app.Model.User;
import com.mera.thar.back_app.Repository.UserRepository;
import com.mera.thar.back_app.Service.UserService;
import com.mera.thar.back_app.Util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {

            if (input == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else if (firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() && password.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_UNAVAILABLE);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validatePassword(password)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_USER_PASSWORD_POLICY);
                response.setResponseData(responseData);
                return response;
            } else {
                User user = this.userRepository.findByEmail(email);

                if (user != null) {
                    responseData.put("user", user);
                    response.setResponseCode(AppConstants.FOUND);
                    response.setResponseMessage(AppConstants.MSG_USER_ALREADY_EXIST);
                    response.setResponseData(responseData);
                    return response;
                } else {
                    String encodedPassword = passwordEncoder.encode(password);

                    user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(email);
                    user.setPassword(encodedPassword);

                    this.userRepository.save(user);

                    responseData.put("user", user);
                    response.setResponseCode(AppConstants.CREATED);
                    response.setResponseMessage(AppConstants.MSG_USER_CREATED_SUCCESSFULLY);
                    response.setResponseData(responseData);
                    return response;
                }
            }
        } catch (Exception ex) {
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

        password = password.toString().trim();

        try {
            if (input == null) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_NO_INPUT);
                response.setResponseData(responseData);
                return response;
            } else if (email.isEmpty() && password.isEmpty()) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_USER_PARAMETERS_UNAVAILABLE);
                response.setResponseData(responseData);
                return response;
            } else if (!SystemUtils.validatePassword(password)) {
                responseData.put("user", null);
                response.setResponseCode(AppConstants.BAD_REQUEST);
                response.setResponseMessage(AppConstants.MSG_USER_PASSWORD_POLICY);
                response.setResponseData(responseData);
                return response;
            } else {
                User user = this.userRepository.findByEmail(email);

                if (user == null) {
                    responseData.put("user", null);
                    response.setResponseCode(AppConstants.BAD_REQUEST);
                    response.setResponseMessage(AppConstants.MSG_NO_USER_EXIST);
                    response.setResponseData(responseData);
                    return response;
                } else {

                    boolean matchPassword = passwordEncoder.matches(password, user.getPassword());
                    if (matchPassword) {
                        responseData.put("user", user);
                        response.setResponseCode(AppConstants.ACCEPTED);
                        response.setResponseMessage(AppConstants.MSG_USER_LOGIN_SUCCESSFULLY);
                        response.setResponseData(responseData);
                        return response;
                    } else {
                        responseData.put("user", null);
                        response.setResponseCode(AppConstants.NOT_FOUND);
                        response.setResponseMessage(AppConstants.MSG_USER_PASSWORD_INCORRECT);
                        response.setResponseData(responseData);
                        return response;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }


}