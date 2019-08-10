package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysOrganize;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.bean.SysUserLogOn;
import com.wt.overflow.dao.SysOrganizeMapper;
import com.wt.overflow.dao.SysUserLogOnMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.SysUserService;
import com.wt.overflow.util.MD5Util;
import com.wt.overflow.util.UUIDUtil;
import com.wt.overflow.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
	@Autowired
	private SysUserLogOnMapper sysUserLogOnMapper;
	@Autowired
	private SysOrganizeMapper sysOrganizeMapper;

	
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

	
	public Integer updateSysUser(HttpServletRequest request, SysUser sysUser, String f_UserPassword, String keyValue) {
		Integer res = 0 ;
		if(keyValue==null||keyValue.equals("")){//新增
			sysUser.setId(UUIDUtil.getUUID());
			sysUser.setCreatortime(new Date());
			sysUser.setCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getId());
			Map<String, Object> resmap= new HashMap<String, Object>();
			resmap.put("loginname", sysUser.getAccount());
			if(!sysUserMapper.queryByLoginName(resmap).isEmpty()){//账户名不重复
				return -1;
			}
			sysUserMapper.addSysUser(sysUser);
			SysUserLogOn sysUserLogOn = new SysUserLogOn();
			sysUserLogOn.setUserpassword(MD5Util.getMD5(f_UserPassword));//加密存储
			sysUserLogOn.setUserid(sysUser.getId());
			sysUserLogOn.setId(UUIDUtil.getUUID());
			res = sysUserLogOnMapper.addSysUserPassWord(sysUserLogOn);//新增密码
		}else{//修改
			/*Map<String, Object> resmap= new HashMap<String, Object>();
			resmap.put("loginname", sysUser.getfAccount());
			if(!sysUserMapper.queryByLoginName(resmap).isEmpty()){//账户名不重复
				return -1;
			}*/
			
			
			sysUser.setId(keyValue);
			sysUser.setLastmodifytime(new Date());
			sysUser.setLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getId());
			sysUserMapper.updateSysUser(sysUser);
			
			SysUserLogOn sysUserLogOn = new SysUserLogOn();
			sysUserLogOn.setUserpassword(MD5Util.getMD5(f_UserPassword));//加密存储
			sysUserLogOn.setUserid(sysUser.getId());
			res = sysUserLogOnMapper.updateSysUserPassWord(sysUserLogOn);//修改密码
		}
		return res;
	}

	
	public Integer deleteSysUser(HttpServletRequest request, String keyValue) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("fId", keyValue);
		parameter.put("F_DeleteUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getId());
		parameter.put("F_DeleteTime", new Date());
		return sysUserMapper.deleteSysUser(parameter);
	}

	
	public SysUser queryOneBySysUserId(String keyValue) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("account",keyValue);
		List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
		//SysUser sysUser = sysUserMapper.queryOneBySysUserId(keyValue);
		if (!sysUserList.isEmpty()){
			SysUser sysUser = sysUserList.get(0);
			if(sysUser.getLastmodifyuserid()!=null&&!(sysUser.getLastmodifyuserid().equals(""))){
				SysUser sysUser1 =sysUserMapper.queryOneBySysUserId(sysUser.getLastmodifyuserid());
				if(sysUser1!=null){
					sysUser.setLastmodifyuserid(sysUser.getRealname());
				}else{
					sysUser.setLastmodifyuserid("无");
				}
			}else{sysUser.setLastmodifyuserid("无");}
			if(sysUser.getCreatoruserid()!=null&&!(sysUser.getCreatoruserid().equals(""))){
				SysUser sysUser1 =sysUserMapper.queryOneBySysUserId(sysUser.getCreatoruserid());
				if(sysUser1!=null){
					sysUser.setCreatoruserid(sysUser.getRealname());
				}else{
					sysUser.setCreatoruserid("无");
				}
			}else{sysUser.setCreatoruserid("无");}
			return sysUser ;
		}
		return null ;
	}

	
	public Integer updateSysUserEnabled(HttpServletRequest request, String keyValue) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("fId", keyValue);
		parameter.put("F_LastModifyUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getId());
		parameter.put("F_LastModifyTime", new Date());
		SysUser sysUser = sysUserMapper.queryOneBySysUserId(keyValue);
		if(sysUser.getEnabledmark()==1){
			parameter.put("F_EnabledMark", 0);
		}else{
			parameter.put("F_EnabledMark", 1);
		}
		return sysUserMapper.updateSysUserEnabled(parameter);
	}
	
	/**
	 * 用户密码重置
	 * @param request
	 * @param keyValue
	 * @return
	 */
	
	public Integer updateSysUserReset(HttpServletRequest request, SysUser sysUser, String keyValue) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("fId", keyValue);
		parameter.put("F_LastModifyUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getId());
		parameter.put("F_LastModifyTime", new Date());
		parameter.put("F_Account", sysUser.getAccount());//登录账号
		parameter.put("F_RealName", sysUser.getRealname());//用户姓名
		sysUserMapper.updateSysUserReset(parameter);
		
		SysUserLogOn sysUserLogOn = new SysUserLogOn();
		sysUserLogOn.setUserpassword(MD5Util.getMD5("123456"));//加密存储 密码重置为123456
		sysUserLogOn.setUserid(keyValue);
		return sysUserLogOnMapper.updateSysUserPassWord(sysUserLogOn);//修改密码
	}
	
	/**
	 * 查询所有未删除的记录
	 */
	
	public List<SysUser> selectAllExitSysUsers() {
		// TODO Auto-generated method stub
		List<SysUser> list=sysUserMapper.selectAllExitSysUsers();
		return list;
	}

	
	public Integer updateSysUserHeadIcon(HttpServletRequest request, String headIcon,String fId) {
		if(StringUtils.isEmpty(fId)){
			return 0;
		}
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("fId", fId);
		parameter.put("headIcon", headIcon);
		return sysUserMapper.updateSysUserHeadIcon(parameter);
	}

	
	public Map<String, Object> querySysUserByOrganId(HttpServletRequest request) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		SysUser sysUser =(SysUser)request.getSession().getAttribute("loginUser");
		parameter.put("data", sysUserMapper.querySysUserByOrganId(sysUser));
		return parameter;
	}

}
