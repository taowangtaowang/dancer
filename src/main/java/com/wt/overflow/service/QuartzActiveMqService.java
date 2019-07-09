package com.wt.overflow.service;

import com.wt.overflow.bean.SysUser;

public interface QuartzActiveMqService {
    boolean simpleSend(SysUser messageStr);

    boolean send(String messageStr);

    String receive();
}
