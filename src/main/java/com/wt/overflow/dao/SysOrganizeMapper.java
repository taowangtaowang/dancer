package com.wt.overflow.dao;


import com.wt.overflow.bean.SysOrganize;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@MapperScan
public interface SysOrganizeMapper {

	public List<SysOrganize> queryAllData(Map<String, Object> parameter);
	
	
	/**    
	* @Description: 删除机构  
	* @author wumin
	* @date 2018-04-08 16:00 
	* @param id
	* @return
	*/
	public Integer deleteOrganize(Map<String, Object> parameter);


	public Integer addOrganize(SysOrganize organize);


	public Integer updateOrganize(SysOrganize organize);


	public Integer updateOrganizeDes(SysOrganize organize);

	SysOrganize queryOneByOrganizeId(String organizeId);


	public List<SysOrganize> queryAllByPId(Map<String, Object> parmap);
	
	/**    
	* @Description: 查询所有一级单位  
	* @date 2018-04-17 19:33 
	* @return
	*/
	public List<SysOrganize> selectAllParentOrganize();
}