package com.sos.signal.admin.service;

import com.sos.signal.admin.mapper.UserMapper;
import com.sos.signal.admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserVo> getUserList() {
        return userMapper.getUserList();
    }

    public UserVo getUserById(int id) {
        return userMapper.getUserById(id);
    }

    public UserVo getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    public void signup(UserVo userVo) {
        if (!userVo.getUsername().equals("") && !userVo.getEmail().equals("")) {
            userVo.setPassword(passwordEncoder.encode(userVo.getPassword));
            userMapper.insertUser(userVo);
        }
    }

    public void withdraw(Long id) {
        userMapper.deleteUser(id);
    }

    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
