package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysItemsdetail;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.SysItemsDetailMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.SysItemsDetailService;
import com.wt.overflow.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 字典小项分类说明
 * @author wangt
 *
 */
@Service
public class SysItemsDetailServiceImpl implements SysItemsDetailService {

	@Autowired
	private SysItemsDetailMapper sysItemsDetailMapper;
	@Autowired
	private SysUserMapper sysUserMapper;


	public List<SysItemsdetail> queryAllData() {
		return sysItemsDetailMapper.queryAllData();
	}

	public List<Map<String, Object>> queryMainRole() {
		return sysItemsDetailMapper.queryMainRole();
	}

	public SysItemsdetail queryOneByFId(String keyword) {
		SysItemsdetail SysItemsdetail = sysItemsDetailMapper.queryOneByFId(keyword);
		if(SysItemsdetail.getLastmodifyuserid()!=null&&!(SysItemsdetail.getLastmodifyuserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(SysItemsdetail.getLastmodifyuserid());
			if(sysUser!=null){
				SysItemsdetail.setLastmodifyuserid(sysUser.getRealname());
			}else{
				SysItemsdetail.setLastmodifyuserid("无");
			}
		}else{SysItemsdetail.setLastmodifyuserid("无");}
		if(SysItemsdetail.getCreatoruserid()!=null&&!(SysItemsdetail.getCreatoruserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(SysItemsdetail.getCreatoruserid());
			if(sysUser!=null){
				SysItemsdetail.setCreatoruserid(sysUser.getRealname());
			}else{
				SysItemsdetail.setCreatoruserid("无");
			}
		}else{SysItemsdetail.setCreatoruserid("无");}
		return SysItemsdetail;
	}

	public Integer deleteItemsDetailByFId(String keyValue, HttpServletRequest request) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("itemsId", keyValue);
		parameter.put("F_DeleteUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getId());
		parameter.put("F_DeleteTime", new Date());
		return sysItemsDetailMapper.deleteItemsDetailByFId(parameter);
	}

	
	public int updateItemsDetail(HttpServletRequest request, String keyValue, String f_ItemName,
			String f_ItemCode, int f_SortCode, int f_IsDefault, int f_EnabledMark, String f_Description,
			String f_ItemId) {
		Integer res = 0;
		if(keyValue==null ||keyValue.equals("")){//新增
			SysItemsdetail  sysItemsdetail = new SysItemsdetail();
			sysItemsdetail.setId(UUIDUtil.getUUID());
			sysItemsdetail.setItemid(f_ItemId);sysItemsdetail.setItemcode(f_ItemCode);
			sysItemsdetail.setItemname(f_ItemName);sysItemsdetail.setSortcode(f_SortCode);
			sysItemsdetail.setIsdefault(f_IsDefault);sysItemsdetail.setEnabledmark(f_EnabledMark);
			sysItemsdetail.setDescription(f_Description);sysItemsdetail.setDeletemark(0);
			sysItemsdetail.setCreatortime(new Date());sysItemsdetail.setCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getId());
			res =sysItemsDetailMapper.addSysItemsDetail(sysItemsdetail);
		}else{//修改
			SysItemsdetail  sysItemsdetail = new SysItemsdetail();
			sysItemsdetail.setItemid(f_ItemId);sysItemsdetail.setItemcode(f_ItemCode);
			sysItemsdetail.setItemname(f_ItemName);sysItemsdetail.setSortcode(f_SortCode);
			sysItemsdetail.setIsdefault(f_IsDefault);sysItemsdetail.setEnabledmark(f_EnabledMark);
			sysItemsdetail.setDescription(f_Description);sysItemsdetail.setDeletemark(0);
			sysItemsdetail.setId(keyValue);
			sysItemsdetail.setLastmodifytime(new Date());;sysItemsdetail.setLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getId());
			res =sysItemsDetailMapper.updateItemsDetail(sysItemsdetail);
		}
		
		return res;
	}

	public List<Map<String, Object>> getSelectJson(String enCode) {
		// TODO Auto-generated method stub
		return sysItemsDetailMapper.getSelectJson(enCode);
	}

}
