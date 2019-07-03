package com.wt.overflow.service;

import java.util.Date;
import java.util.Map;

public interface SysCheckinInService {

	Map<String, Object> queryAllCheckInDetail(Date startTime, Date endTime, String userName);

	Map<String, Object> queryAlllistDetail(Date startTime, Date endTime, String userName, String typeString);

}
