package com.wt.overflow.service.impl;

import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.SysUserService;
import com.wt.overflow.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户相关
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	
	public Map<String, Object> queryUserByPage(Integer rows, Integer currpage, String sidx, String sord, String keyword) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Page page = new Page();
		page.setCurrentPage(currpage);
		page.setOrder(sord);
		page.setSortField(sidx);
		page.setShowCount(rows);
		if(StringUtils.isEmpty(sord)){
			sord="asc";
		}
		if(sidx.equals("realname")){
			sidx = "realname";
		}else if (sidx.equals("dutyid")){
			sidx = "dutyid";
		}else if (sidx.equals("departmentid")){
			sidx = "departmentid";
		}else if (sidx.equals("organizeid")){
			sidx = "organizeid";
		}else if (sidx.equals("description")){
			sidx = "description";
		}else if (sidx.equals("creatorTime")){
			sidx = "creatorTime";
		}else if (sidx.equals("mobilePhone")){
			sidx = "mobilePhone";
		}else if (sidx.equals("gender")){
			sidx = "gender";
		}else{
			sidx = "realname";
		}
		parameter.put("page", page);
		parameter.put("sidx", sidx);
		parameter.put("sord", sord);
		parameter.put("keyword", keyword);
		List<Map<String, Object>> SysUserlist =  sysUserMapper.queryUserByPage(parameter);
		
		page.setList(SysUserlist);
		Map<String, Object> resmap= new HashMap<String, Object>();
		resmap.put("rows", SysUserlist);
		resmap.put("total", page.getTotalPage());
		resmap.put("page", currpage);
		resmap.put("records", page.getTotalResult());
		return resmap;
				
	}



}
