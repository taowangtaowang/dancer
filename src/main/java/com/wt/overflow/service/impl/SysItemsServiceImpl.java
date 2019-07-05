package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysItems;
import com.wt.overflow.bean.SysUser;
import com.wt.overflow.dao.SysItemsDetailMapper;
import com.wt.overflow.dao.SysItemsMapper;
import com.wt.overflow.dao.SysUserMapper;
import com.wt.overflow.service.SysItemsService;
import com.wt.overflow.util.UUIDUtil;
import com.wt.overflow.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 字典主项分类说明
 * @author wangt
 *
 */
@Service
public class SysItemsServiceImpl implements SysItemsService {

	@Autowired
	private SysItemsMapper sysItemsMapper;
	@Autowired
	private SysItemsDetailMapper sysItemsDetailMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	public List<SysItems> queryAllData() {
		return sysItemsMapper.queryAllData();
	}

	public List<Map<String, Object>> queryAllDataByZtree() {
		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
		//返回字典数据ztree结构
		List<SysItems> sysItemslist = sysItemsMapper.queryAllData();//主表所有数据
		if(!sysItemslist.isEmpty()){	
			for (SysItems sysItems : sysItemslist) {
				if(sysItems.getParentid().equals("0")){//顶级节点
					Map<String, Object> resmap = new HashMap<String, Object>();
					resmap.put("id", sysItems.getId());
					resmap.put("text", sysItems.getFullname());
					resmap.put("value", sysItems.getEncode());
					resmap.put("parentnodes", sysItems.getParentid());
					resmap.put("showcheck", true);
					resmap.put("isexpand", true);
					resmap.put("complete", true);
					Map<String, Object> parmap = new HashMap<String, Object>();
					parmap.put("fId", sysItems.getId());
					List<SysItems> resChildslist = sysItemsMapper.queryAllByPId(parmap);
					resmap.put("hasChildren", resChildslist.isEmpty()?false:true);
					
					List<Map<String, Object>> childslist = new ArrayList<Map<String, Object>>();
					if(!resChildslist.isEmpty()){
						for (SysItems childItmes : resChildslist) {
							Map<String, Object> childmap = new HashMap<String, Object>();
							childmap.put("id", childItmes.getId());
							childmap.put("text", childItmes.getFullname());
							childmap.put("value", childItmes.getEncode());
							childmap.put("parentnodes", childItmes.getParentid());
							childmap.put("showcheck", true);
							childmap.put("isexpand", true);
							childmap.put("complete", true);
							childmap.put("hasChildren", false);
							childmap.put("ChildNodes", new ArrayList<Map<String, Object>>());
							childslist.add(childmap);
						}
					}
					
					//子数据
					resmap.put("ChildNodes", childslist);
					reslist.add(resmap);
				}
			}
		}
		return reslist;
	}
	

	/**
	 * 字典数据分页查询
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param keyword	关键字查询（筛选F_FullName或F_EnCode字段）
	 * @param itemId 	模糊查询对应Sys_Items表F_Id
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 上午10:46:35
	 */
	public List<Map<String, Object>> queryAllDataByPage(Integer rows, Integer currpage, String sidx, String sord,
			String keyword,String itemId) {
		// TODO Auto-generated method stub
		Map<String, Object> parameter = new HashMap<String, Object>();
		Page page = new Page();
		if(sidx.equals("fItemcode")){
			sidx="F_Itemcode";
		}else if (sidx.equals("fSortcode")){
			sidx="F_Sortcode";
		}else if (sidx.equals("fIsdefault")){
			sidx="F_Isdefault";
		}else if (sidx.equals("fCreatortime")){
			sidx="F_Creatortime";
		}else if (sidx.equals("fEnabledmark")){
			sidx="F_Enabledmark";
		}else{
			sidx="F_Sortcode";
		}
		if(StringUtils.isEmpty(sord)){
			sord="asc";
		}
		page.setCurrentPage(currpage);
		page.setOrder(sord);
		page.setSortField(sidx);
		page.setShowCount(rows);
		parameter.put("sidx", sidx);
		parameter.put("sord", sord);
		parameter.put("keyword", keyword);
		parameter.put("itemId", itemId);
		parameter.put("page", page);
		return sysItemsDetailMapper.queryAllDataByPage(parameter);//主表所有数据
	}

	
	public Integer deleteItemsByFId(String keyword,HttpServletRequest request) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("itemsId", keyword);
		parameter.put("F_DeleteUserId", ((SysUser)request.getSession().getAttribute("loginUser")).getId());
		parameter.put("F_DeleteTime", new Date());
		return sysItemsMapper.deleteItemsByFId(parameter);
	}

	
	public SysItems queryOneByFId(String keyword) {
		// TODO Auto-generated method stub
		SysItems sysItems = sysItemsMapper.queryOneById(keyword);
		if(sysItems.getLastmodifyuserid()!=null&&!(sysItems.getLastmodifyuserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(sysItems.getLastmodifyuserid());
			if(sysUser!=null){
				sysItems.setLastmodifyuserid(sysUser.getRealname());
			}else{
				sysItems.setLastmodifyuserid("无");
			}
		}else{sysItems.setLastmodifyuserid("无");}
		if(sysItems.getCreatoruserid()!=null&&!(sysItems.getCreatoruserid().equals(""))){
			SysUser sysUser =sysUserMapper.queryOneBySysUserId(sysItems.getCreatoruserid());
			if(sysUser!=null){
				sysItems.setCreatoruserid(sysUser.getRealname());
			}else{
				sysItems.setCreatoruserid("无");
			}
		}else{sysItems.setCreatoruserid("无");}
		
		return sysItems;
	}

	
	public int updateItems(HttpServletRequest request, String keyValue, String f_FullName,
			String f_EnCode, int f_SortCode, int f_EnabledMark, String f_ParentId,String fDescription) {
		Integer res = 0;
		if(keyValue==null ||keyValue.equals("")){//新增
			if(f_EnCode==null||f_EnCode.equals("")){//编号必须唯一
				return -1;
			}
			
			SysItems sysItems = new SysItems();
			sysItems.setId(UUIDUtil.getUUID());sysItems.setDescription(fDescription);
			sysItems.setParentid(f_ParentId);sysItems.setFullname(f_FullName);
			sysItems.setEncode(f_EnCode);sysItems.setSortcode(f_SortCode);
			sysItems.setDeletemark(0);sysItems.setEnabledmark(f_EnabledMark);
			
			List<SysItems> enCodelist = sysItemsMapper.queryByEnCode(sysItems);
			if(!enCodelist.isEmpty()){
				return -99;
			}
			
			sysItems.setCreatortime(new Date());sysItems.setCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getId());
			res =sysItemsMapper.addSysItems(sysItems);
		}else{//修改
			SysItems sysItems = new SysItems();
			sysItems.setParentid(f_ParentId);sysItems.setFullname(f_FullName);
			sysItems.setEncode(f_EnCode);sysItems.setSortcode(f_SortCode);
			sysItems.setDeletemark(0);sysItems.setEnabledmark(f_EnabledMark);
			sysItems.setId(keyValue);
			sysItems.setLastmodifytime(new Date());;sysItems.setLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getId());
			res =sysItemsMapper.updateItems(sysItems);
		}
		
		return res;
	}

	
	public Map<String, Object> queryAllItemsList() {
		List<SysItems> reslist = sysItemsMapper.queryAllData();
		List<SysItems> haveChildlist = new ArrayList<SysItems>();//有子节点的数据
		List<SysItems> nohaveChildlist = new ArrayList<SysItems>();//没有子节点的数据
		List<SysItems> newreslist = new ArrayList<SysItems>();//有子节点的数据
		
		List<Map<String, Object>> resmaplist = new ArrayList<Map<String, Object>>();
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(!reslist.isEmpty()){
			//筛选有子节点d顶级数据
			for (SysItems sysItems : reslist) {
				if(sysItems.getParentid().equals("0")){//顶级节点
					boolean is = false;
					for (SysItems childs : reslist) {
						if(childs.getParentid().equals(sysItems.getId())){//有子节点
							is = true ;
						}
					}
					if(is){
						haveChildlist.add(sysItems);
					}else{
						nohaveChildlist.add(sysItems);
					}
				}
			}
			// 新的list   有子级的顶级节点    节点随后的节点  然后是没有子级的顶级节点
			for (SysItems havechildsys : haveChildlist) {
				newreslist.add(havechildsys);
				for (SysItems sysItems : reslist) {
					if(sysItems.getParentid().equals(havechildsys.getId())){
						newreslist.add(sysItems);
					}
				}
			}
			for (SysItems nohavechild: nohaveChildlist) {
				newreslist.add(nohavechild);
			}
			
			for (SysItems sysItems : newreslist) {
				Map<String, Object> map = new HashMap<String, Object>();
				if(sysItems.getParentid().toString().equals("0")){//第一级
					map.put("level", "0");
				}else{
					map.put("level", "1");
				}
				map.put("parent", sysItems.getParentid());
				map.put("expanded", true);
				map.put("fEncode", sysItems.getEncode());
				map.put("fSortcode", sysItems.getSortcode());
				map.put("loaded", true);
				map.put("fEnabledmark", sysItems.getEnabledmark());
				map.put("fDescription", sysItems.getDescription());
				Map<String, Object> parmap = new HashMap<String, Object>();
				parmap.put("fId", sysItems.getId());
				if(sysItemsMapper.queryAllByPId(parmap).isEmpty()){
					map.put("isLeaf", true);
				}else{
					map.put("isLeaf", false);
				}
				map.put("fId", sysItems.getId());
				map.put("fFullname", sysItems.getFullname());
				map.put("parentId", sysItems.getParentid());
				resmaplist.add(map );
			}
			
			
			resmap.put("rows", resmaplist);
		}
		return resmap;
	}
}
