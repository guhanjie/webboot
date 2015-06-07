package com.guhanjie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guhanjie.mapper.UserMapper;
import com.guhanjie.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUser(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
