package com.wt.overflow.service;

import com.wt.overflow.bean.SysOrganize;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysOrganizeService {

	public Map<String, Object> queryAllData(Integer rows, Integer page, String sidx, String sord, String keyword, String nodeid, String n_level) throws Exception;
	

}
