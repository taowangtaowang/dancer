package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysModule;
import com.wt.overflow.bean.SysModules;
import com.wt.overflow.bean.SysOnes;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.SysModuleMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.SysModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单相关
 * @author wangt
 *
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Autowired
	private SysModuleMapper sysModuleMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	public SysModule selectByPrimaryKey(String fId) {
		SysModule sysModule = sysModuleMapper.selectByPrimaryKey(fId);
		if(sysModule.getLastmodifyuserid()!=null&&!(sysModule.getLastmodifyuserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(sysModule.getLastmodifyuserid());
			if(sysUser!=null){
				sysModule.setLastmodifyuserid(sysUser.getRealname());
			}else{
				sysModule.setLastmodifyuserid("无");
			}
		}else{sysModule.setLastmodifyuserid("无");}
		if(sysModule.getCreatoruserid()!=null&&!(sysModule.getCreatoruserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(sysModule.getCreatoruserid());
			if(sysUser!=null){
				sysModule.setCreatoruserid(sysUser.getRealname());
			}else{
				sysModule.setCreatoruserid("无");
			}
		}else{sysModule.setCreatoruserid("无");}
		return sysModule;
	
		
	}

	
	public boolean insertSysModule(SysModule sysModule) {
	   byte b =0;
	   sysModule.setDeletemark(b);
		return sysModuleMapper.insertModule(sysModule);
	}

	
	public boolean deleteByModuleKey(SysModule sysModule) {
		byte b =1;
		sysModule.setDeletemark(b);
		return sysModuleMapper.deleteByModuleKey(sysModule);
	}

	
	public boolean updateByPrimaryKeySelective(SysModule sysModule) {
		return sysModuleMapper.updateByPrimaryKeySelective(sysModule);
	}

	
	public List<SysModules> findListParentid(String Parentid) {
		return sysModuleMapper.findListParentid(Parentid);
	}

	
	public SysModule SelectByfParentid(String fParentid) {
		return sysModuleMapper.SelectByfParentid(fParentid);
	}

	
	public List<SysOnes> findList() {
		return sysModuleMapper.findList();
	}

	
	public List<SysModules> findListS(String fFullname) {
		if (null != fFullname && "" != fFullname) {
			fFullname = "%" + fFullname + "%";
		}
		return sysModuleMapper.findListFullName(fFullname);
	}

	


	

}
