package com.wt.overflow.service;

import com.wt.overflow.bean.Account;

public interface QuartzActiveMqService {
    boolean simpleSend(Account account);

    boolean send(String messageStr);

    String receive();
}
