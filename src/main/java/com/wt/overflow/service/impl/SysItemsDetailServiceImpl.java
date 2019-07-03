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
		if(SysItemsdetail.getfLastmodifyuserid()!=null&&!(SysItemsdetail.getfLastmodifyuserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(SysItemsdetail.getfLastmodifyuserid());
			if(sysUser!=null){
				SysItemsdetail.setfLastmodifyuserid(sysUser.getfRealname());
			}else{
				SysItemsdetail.setfLastmodifyuserid("无");
			}
		}else{SysItemsdetail.setfLastmodifyuserid("无");}
		if(SysItemsdetail.getfCreatoruserid()!=null&&!(SysItemsdetail.getfCreatoruserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(SysItemsdetail.getfCreatoruserid());
			if(sysUser!=null){
				SysItemsdetail.setfCreatoruserid(sysUser.getfRealname());
			}else{
				SysItemsdetail.setfCreatoruserid("无");
			}
		}else{SysItemsdetail.setfCreatoruserid("无");}
		return SysItemsdetail;
	}

	public Integer deleteItemsDetailByFId(String keyValue, HttpServletRequest request) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("itemsId", keyValue);
		parameter.put("F_DeleteUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getfId());
		parameter.put("F_DeleteTime", new Date());
		return sysItemsDetailMapper.deleteItemsDetailByFId(parameter);
	}

	
	public Integer updateItemsDetail(HttpServletRequest request, String keyValue, String f_ItemName,
			String f_ItemCode, Integer f_SortCode, boolean f_IsDefault, boolean f_EnabledMark, String f_Description,
			String f_ItemId) {
		Integer res = 0;
		if(keyValue==null ||keyValue.equals("")){//新增
			SysItemsdetail  sysItemsdetail = new SysItemsdetail();
			sysItemsdetail.setfId(UUIDUtil.getUUID());
			sysItemsdetail.setfItemid(f_ItemId);sysItemsdetail.setfItemcode(f_ItemCode);
			sysItemsdetail.setfItemname(f_ItemName);sysItemsdetail.setfSortcode(f_SortCode);
			sysItemsdetail.setfIsdefault(f_IsDefault);sysItemsdetail.setfEnabledmark(f_EnabledMark);
			sysItemsdetail.setfDescription(f_Description);sysItemsdetail.setfDeletemark(false);
			sysItemsdetail.setfCreatortime(new Date());sysItemsdetail.setfCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			res =sysItemsDetailMapper.addSysItemsDetail(sysItemsdetail);
		}else{//修改
			SysItemsdetail  sysItemsdetail = new SysItemsdetail();
			sysItemsdetail.setfItemid(f_ItemId);sysItemsdetail.setfItemcode(f_ItemCode);
			sysItemsdetail.setfItemname(f_ItemName);sysItemsdetail.setfSortcode(f_SortCode);
			sysItemsdetail.setfIsdefault(f_IsDefault);sysItemsdetail.setfEnabledmark(f_EnabledMark);
			sysItemsdetail.setfDescription(f_Description);sysItemsdetail.setfDeletemark(false);
			sysItemsdetail.setfId(keyValue);
			sysItemsdetail.setfLastmodifytime(new Date());;sysItemsdetail.setfLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			res =sysItemsDetailMapper.updateItemsDetail(sysItemsdetail);
		}
		
		return res;
	}

	public List<Map<String, Object>> getSelectJson(String enCode) {
		// TODO Auto-generated method stub
		return sysItemsDetailMapper.getSelectJson(enCode);
	}

}
