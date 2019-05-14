package com.karu.dao;

import com.karu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * create User data access to table
 *
 * @author StevenWu
 * @create 2019-05-14-23:57
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
