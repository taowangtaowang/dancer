package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysRole;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.SysRoleMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.SysRoleService;
import com.wt.overflow.util.UUIDUtil;
import com.wt.overflow.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 所属角色相关 1 角色 2 岗位
 * 
 * @author wangt
 *
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 分页查询


	 * @param rows
	 *            每页显示数量
	 *
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式【asc，desc】
	 * @param keyword
	 *            关键字查询（筛选F_FullName或F_EnCode字段）
	 * @return
	 *
	 * @name wangt
	 * @Date 2018年4月8日 下午3:46:50
	 */
	
	public Map<String, Object> queryRoleByPage(Integer rows, Integer currpage, String sidx, String sord, String keyword) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		Map<String, Object> parameter = new HashMap<String, Object>();
		Page page = new Page();
		page.setCurrentPage(currpage);
		page.setOrder(sord);
		page.setSortField(sidx);
		page.setShowCount(rows);
		parameter.put("page", page);
		parameter.put("sidx", sidx);
		parameter.put("sord", sord);
		parameter.put("keyword", keyword);
		parameter.put("roleType", 1);
		List<Map<String, Object>> SysRolelist = sysRoleMapper.queryRoleByPage(parameter);
		page.setList(SysRolelist);
		resmap.put("state", "success");
		resmap.put("message", "分页查询成功");
		resmap.put("data", SysRolelist);
		return resmap;
	}

	
	public Integer deleteRole(HttpServletRequest request, String roleId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("roleId", roleId);
		parameter.put("F_DeleteUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getfId());
		parameter.put("F_DeleteTime", new Date());
		return sysRoleMapper.deleteRole(parameter);
	}

	

	/***************************************岗位相关******************************************/
	/**
	 * 分页查询岗位所有数据
	 *
	 * @param rows
	 *
	 * @param sidx
	 * @param sord
	 * @param keyword
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 下午4:58:12
	 */
	
	public List<Map<String, Object>> queryAllDuty(Integer rows, Integer currpage, String sidx, String sord, String keyword) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Page page = new Page();
		page.setCurrentPage(currpage);
		page.setOrder(sord);
		page.setSortField(sidx);
		page.setShowCount(rows);
		parameter.put("page", page);
		parameter.put("sidx", sidx);
		parameter.put("sord", sord);
		parameter.put("keyword", keyword);
		parameter.put("roleType", 2);
		return sysRoleMapper.queryRoleByPage(parameter);
	}

	
	public SysRole queryOneByRoleId(String roleId) {
		SysRole sysRole = sysRoleMapper.queryOneByRoleId(roleId);
		if(sysRole.getfLastmodifyuserid()!=null&&!(sysRole.getfLastmodifyuserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(sysRole.getfLastmodifyuserid());
			if(sysUser!=null){
				sysRole.setfLastmodifyuserid(sysUser.getfRealname());
			}else{
				sysRole.setfLastmodifyuserid("无");
			}
		}else{sysRole.setfLastmodifyuserid("无");}
		if(sysRole.getfCreatoruserid()!=null&&!(sysRole.getfCreatoruserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(sysRole.getfCreatoruserid());
			if(sysUser!=null){
				sysRole.setfCreatoruserid(sysUser.getfRealname());
			}else{
				sysRole.setfCreatoruserid("无");
			}
		}else{sysRole.setfCreatoruserid("无");}
		return sysRole;
	}

	
	/**
	 * @param sysRole
	 * @param keyword
	 * @return
	 * @name wangt	
	 * @Date 2018年4月9日 下午5:25:26
	 */
	
	public Map<String, Object> updateDuty(HttpServletRequest request,SysRole sysRole, String keyword) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(keyword.equals("")){//新政
			sysRole.setfId(UUIDUtil.getUUID());
			sysRole.setfCategory(2);
			sysRole.setfCreatortime(new Date());
			sysRole.setfCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			Map<String, Object> parmap = new HashMap<String, Object>();
			parmap.put("sysRole", sysRole);
			sysRole.setfDeletemark(false);
			sysRoleMapper.addRole(parmap);//新增角色 	返回主键
		}else{
			sysRole.setfId(keyword);
			sysRole.setfCategory(2);
			sysRole.setfLastmodifytime(new Date());
			sysRole.setfLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			Map<String, Object> parmap = new HashMap<String, Object>();
			parmap.put("sysRole", sysRole);
			sysRoleMapper.updateRoleByRoleId(parmap);//新增角色 	返回主键
		}
		resmap.put("state", "success");
		resmap.put("message", "分页查询成功");
		return resmap;
	}
	
}
