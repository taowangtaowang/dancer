package com.wt.overflow.service;

import com.wt.overflow.bean.SysUser;

import java.util.List;
import java.util.Map;


public interface LoginService {

	List<SysUser> queryByLoginName(String loginname);

	Map<String, Object> queryInitData(SysUser sysUser);

}
