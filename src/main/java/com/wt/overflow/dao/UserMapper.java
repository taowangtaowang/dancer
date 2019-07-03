package com.wt.overflow.dao;

import com.wt.overflow.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

	List<User> selectAllByPage(User user);

}
