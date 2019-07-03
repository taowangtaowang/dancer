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

	
	public Object queryOneByOrganizeId(String organizeId) {
		return sysOrganizeMapper.queryOneByOrganizeId(organizeId);
	}

	
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
		if(sidx.equals("fEncode")){
			sidx = "F_Encode";
		}else if (sidx.equals("fCategoryId")){
			sidx = "F_CategoryId";
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
				
				map.put("parent", sysOrganize.getfParentid());
				map.put("expanded", false);
				map.put("fEncode", sysOrganize.getfEncode());
				map.put("fSortcode", sysOrganize.getfSortcode());
				map.put("loaded", false);
				map.put("fEnabledmark", sysOrganize.getfEnabledmark());
				map.put("fDescription", sysOrganize.getfDescription());
				Map<String, Object> parmap = new HashMap<String, Object>();
				parmap.put("fId", sysOrganize.getfId());
				if(sysOrganizeMapper.queryAllByPId(parmap).isEmpty()){
					map.put("isLeaf", true);
				}else{
					map.put("isLeaf", false);
				}
				map.put("fId", sysOrganize.getfId());
				map.put("fFullname", sysOrganize.getfFullname());
				map.put("parentId", sysOrganize.getfParentid());
				resmaplist.add(map );
			}
			resmap.put("rows", resmaplist);
		}
		return resmap;
		
		/*List<SysOrganize> list= sysOrganizeMapper.queryAllData(parameter);
		resmap.put("state", "success");
		resmap.put("message", "分页查询成功");
		
		List<Map<Object,Object>> resMaplist = new ArrayList<Map<Object,Object>>();
		for (SysOrganize sysOrganize : list) {
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			detailmap.put("id", sysOrganize.getfId());
			detailmap.put("text", sysOrganize.getfFullname());
			detailmap.put("parentId", sysOrganize.getfParentid());
			Map<String, Object> turnmap = ObjectUtil.convertBean(sysOrganize);
			detailmap.put("data", turnmap);
			resMaplist.add(detailmap);
		}
		resmap.put("data", resMaplist);
		return resmap;*/
	}

	

	
	public List<SysOrganize> queryChildsDataByFId(String keyword) {
		Map<String, Object> parmap = new HashMap<String, Object>();
		parmap.put("fId", keyword);
		return sysOrganizeMapper.queryAllByPId(parmap);
	}
	
	
	
	public Integer updateOrganize(SysOrganize organize, String keyword, HttpServletRequest request) {
		Integer isSuccess = 0;
		
		if(keyword.equals("")){//新增
			organize.setfId(UUIDUtil.getUUID());
			organize.setfCreatortime(new Date());
			organize.setfCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			Map<String, Object> parmap = new HashMap<String, Object>();
			parmap.put("organize", organize);
			isSuccess = sysOrganizeMapper.addOrganize(organize);
		}else{//修改
			organize.setfId(keyword);
			organize.setfLastmodifytime(new Date());
			organize.setfLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			Map<String, Object> parmap = new HashMap<String, Object>();
			parmap.put("organize", organize);
			isSuccess = sysOrganizeMapper.updateOrganize(organize);
		}
		return isSuccess;
	}

	
	public Integer deleteOrganize(HttpServletRequest request, String keyValue) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("roleId", keyValue);
		parameter.put("F_DeleteUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getfId());
		parameter.put("F_DeleteTime", new Date());
		return sysOrganizeMapper.deleteOrganize(parameter);
	}


	
	public Integer updateOrganizeDes(HttpServletRequest request, String f_Description,String f_Id) {
		SysOrganize organize = new SysOrganize();
		organize.setfId(f_Id);organize.setfDescription(f_Description);
		organize.setfLastmodifytime(new Date());
		organize.setfLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
		return sysOrganizeMapper.updateOrganizeDes(organize);
	}

	
	public String queryAllDataZtree() {
		List<SysOrganize> list = sysOrganizeMapper.queryAllData(new HashMap<String, Object>());
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (SysOrganize sysOrganize : list) {// 单位
			sb.append("{fId:'" + sysOrganize.getfId() + "',pId:'"+ sysOrganize.getfParentid()+ "',name:'" + sysOrganize.getfFullname()+ "',fOrganizename:'"+sysOrganize.getfFullname()+"'},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

	
	public List<SysOrganize> selectAllParentOrganize() {
		return sysOrganizeMapper.selectAllParentOrganize();
	}

}
