package com.karu.service;

import com.karu.dao.UserRepository;
import com.karu.domain.User;
import com.karu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * create UserService implements
 *
 * @author StevenWu
 * @create 2019-05-14-23:52
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
