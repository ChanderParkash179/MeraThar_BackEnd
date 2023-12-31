package com.mera.thar.back_app.Service;

import com.mera.thar.back_app.Entity.Response;

import java.util.Map;

public interface UserService {

    Response userSignUp(Map<String, Object> input);

    Response userSignIn(Map<String, Object> input);

    Response findByEmail(Map<String, Object> input);

    Response userEdit(Map<String, Object> input);

    Response list();

    Response deleteByEmail(Map<String, Object> input);
}
