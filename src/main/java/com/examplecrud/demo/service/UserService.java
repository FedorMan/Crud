package com.examplecrud.demo.service;

import com.examplecrud.demo.entity.User;
import com.examplecrud.demo.mapper.RoleMapper;
import com.examplecrud.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userMapper.create(user.getUsername(),user.getPassword());
         user = userMapper.read(user.getUsername());
         userMapper.addRole(user.getId(),2L);
    }

    public User findByName(String name){
        User user = userMapper.read(name);
        return user;
    }

}
