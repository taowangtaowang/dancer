package com.wt.overflow.service;

import com.wt.overflow.bean.SysOrganize;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface SysOrganizeService {

	public Map<String, Object> queryAllData(Integer rows, Integer page, String sidx, String sord, String keyword, String nodeid, String n_level) throws Exception;
	
	
	/**    
	* @Description: 修改机构  
	* @author wumin
	* @date 2018-04-08 15:47 
	* @param organize
	 * @param request 
	 * @param keyword 
	* @return
	*/
	public Integer updateOrganize(SysOrganize organize, String keyword, HttpServletRequest request);
	
	/**    
	* @Description: 删除机构  
	* @author wumin
	* @date 2018-04-08 16:05 
	* @param request 机构ID
	* @param deleteUserId 删除用户ID
	* @return
	*/
	public Integer deleteOrganize(HttpServletRequest request, String deleteUserId);


	public Integer updateOrganizeDes(HttpServletRequest request, String f_Description, String f_Id);

	Object queryOneByOrganizeId(String keyValue);


	public List<SysOrganize> queryChildsDataByFId(String keyword);


	public String queryAllDataZtree();
	
	/**    
	* @Description: 查询所有一级单位  
	* @date 2018-04-17 19:33 
	* @return
	*/
	public List<SysOrganize> selectAllParentOrganize();
}
