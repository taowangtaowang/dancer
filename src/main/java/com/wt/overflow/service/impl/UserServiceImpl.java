package com.wt.overflow.service.impl;

import com.wt.overflow.bean.User;
import com.wt.overflow.dao.UserMapper;
import com.wt.overflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> selectByCondition(User user) {
		return userMapper.selectAllByPage(user);
	}

}
