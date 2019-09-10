package com.wt.overflow.service;

import com.wt.overflow.bean.LoginLog;

import java.util.List;

public interface LoginLogService {

    void add(LoginLog opLog);

    void adds(List<LoginLog> loginLogList);
}
