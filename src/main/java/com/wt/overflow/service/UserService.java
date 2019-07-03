package com.wt.overflow.service;

import com.wt.overflow.bean.User;

import java.util.List;

public interface UserService {

	List<User> selectByCondition(User user);

}
