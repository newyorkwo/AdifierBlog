package com.karu.service;

import com.karu.domain.User;

public interface UserService {

    User checkUser(String username, String password);
}
