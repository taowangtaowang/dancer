package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysOrganize;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.SysOrganizeMapper;
import com.wt.overflow.service.SysOrganizeService;
import com.wt.overflow.util.UUIDUtil;
import com.wt.overflow.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 所属单位相关
 * @author wangt
 */
@Service("sysOrganizeService")
public class SysOrganizeServiceImpl implements SysOrganizeService {

	@Autowired
	private SysOrganizeMapper sysOrganizeMapper;

	public Map<String, Object> queryAllData(Integer rows,Integer currpage, String sidx, String sord, String keyword,String nodeid,String n_level) throws Exception {
		
		Map<String, Object> parameter = new HashMap<String, Object>();
		Page page = new Page();
		page.setCurrentPage(currpage);
		page.setOrder(sord);
		page.setSortField(sidx);
		page.setShowCount(rows);
		if(StringUtils.isEmpty(nodeid)){
			nodeid="0";
		}
		if(sidx.equals("encode")){
			sidx = "encode";
		}else if (sidx.equals("categoryId")){
			sidx = "categoryId";
		}else {
			
		}
		parameter.put("page", page);
		parameter.put("sidx", sidx);
		parameter.put("sord", sord);
		parameter.put("keyword", keyword);
		parameter.put("nodeid", nodeid);
		List<SysOrganize> reslist= sysOrganizeMapper.queryAllData(parameter);
		
		List<Map<String, Object>> resmaplist = new ArrayList<Map<String, Object>>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(!reslist.isEmpty()){
			for (SysOrganize sysOrganize : reslist) {
				Map<String, Object> map = new HashMap<String, Object>();
				if(StringUtils.isEmpty(n_level)){
					map.put("level", "0");
				}else{
					map.put("level", Integer.parseInt(n_level)+1);
				}
				
				map.put("parent", sysOrganize.getParentid());
				map.put("expanded", false);
				map.put("encode", sysOrganize.getEncode());
				map.put("sortcode", sysOrganize.getSortcode());
				map.put("loaded", false);
				map.put("enabledmark", sysOrganize.getEnabledmark());
				map.put("description", sysOrganize.getDescription());
				Map<String, Object> parmap = new HashMap<String, Object>();
				parmap.put("id", sysOrganize.getId());
				map.put("isLeaf", false);
				map.put("id", sysOrganize.getId());
				map.put("fullname", sysOrganize.getFullname());
				map.put("parentId", sysOrganize.getParentid());
				resmaplist.add(map );
			}
			resmap.put("rows", resmaplist);
		}
		return resmap;
	}


}
