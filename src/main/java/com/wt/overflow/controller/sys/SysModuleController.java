package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysModule;
import com.wt.overflow.bean.SysModules;
import com.wt.overflow.service.SysModuleService;
import com.wt.overflow.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统菜单controller
 *
 */
@Controller
@RequestMapping(value = "sysModule")
public class SysModuleController {
	@Autowired
	private SysModuleService sysModuleService;

	/**
	 * 关键字查询菜单信息
	 * @param request
	 * @param fullname
	 * @param nodeid
	 * @return
	 */
	@RequestMapping("findList")
	@ResponseBody
	public Object searchsysModule(HttpServletRequest request, String fullname, String nodeid) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (null != nodeid && "" != nodeid) {// 查询某菜单父节点子数据
			List<SysModules> sysModules = sysModuleService.findListParentid(nodeid);
			for (SysModules Modules : sysModules) {
				Modules.ModulesList();
				SysModule sas = sysModuleService.SelectByfParentid(Modules.getId());
				if (null == sas) {
					//Modules.setIsLeaf(1);
				} else {
					//Modules.setIsLeaf(0);
				}
			}
			data.put("rows", sysModules);
		} else {
			List<SysModules> arealist = sysModuleService.findListS(fullname);
			for (SysModules sysModules : arealist) {
				sysModules.ModulesList();
				if (null == fullname || "" == fullname) {
					//sysModules.setIsLeaf(false);
				}else {
					//sysModules.setIsLeaf(true);
					sysModules.setExpanded(true);
					sysModules.setLoaded(true);
				}

			}
			data.put("rows", arealist);
		}

		return data;
	}


	/**
	 * 查看一条数据
	 * @param request
	 * @param fId
	 * @return
	 */
	@RequestMapping(value = "/findOne")
	@ResponseBody
	public Object selectByPrimaryKey(HttpServletRequest request, String fId) {
		Map<String, Object> datas = new HashMap<String, Object>();
		SysModule sysModule = sysModuleService.selectByPrimaryKey(fId);
		datas.put("sysModule", sysModule);
		return datas;

	}

	/**
	 * 添加
	 * @param request
	 * @param sysModule
	 * @return
	 */
	@RequestMapping(value = "/insertModule")
	@ResponseBody
	public Object insertModule(HttpServletRequest request, SysModule sysModule) {
		sysModule.setId(UUIDUtil.getUUID());
		sysModule.setCreatortime(new Date());
		String fParentid = sysModule.getParentid();
		Integer fLayers = sysModule.getLayers();

		if("0".equals(fParentid)){
			boolean datas = sysModuleService.insertSysModule(sysModule);
			Map<String, Object> data=new HashMap<String,Object>();
			if(datas) {
				data.put("message", "添加成功");
				data.put("state","success");

			}else {
				data.put("message", "添加失败");
				data.put("state","error");

			}
			data.put("data", "");
			return data;
		}


		if(!"0".equals(fParentid)){
			SysModule sysModu = sysModuleService.selectByPrimaryKey(fParentid);
			sysModu.setLayers(1);
			boolean datas = sysModuleService.updateByPrimaryKeySelective(sysModu);
			Map<String, Object> data=new HashMap<String,Object>();
		}
		sysModule.setLayers(2);
		boolean datas = sysModuleService.insertSysModule(sysModule);
		Map<String, Object> data=new HashMap<String,Object>();
		if(datas) {
			data.put("message", "添加成功");
			data.put("state","success");

		}else {
			data.put("message", "添加失败");
			data.put("state","error");

		}
		data.put("data", "");
		return data;

	}

	/**
	 * 逻辑删除
	 * @param sysModule
	 * @return
	 */
	@RequestMapping("deleModule")
	@ResponseBody
	public Object deleComplaint(SysModule sysModule) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = sysModule.getId();
		sysModule.setDeletetime(new Date());
		boolean updataByPrimaryKey = false;
		if (null != id) {
			updataByPrimaryKey = sysModuleService.deleteByModuleKey(sysModule);
		}
		if (updataByPrimaryKey) {
			map.put("status", "success");
			map.put("message", "删除成功");
			map.put("data", updataByPrimaryKey);
			return map;
		} else {
			map.put("status", "failure");
			map.put("message", "删除异常");
			map.put("data", updataByPrimaryKey);
			return map;
		}
	}

	/**
	 * 修改
	 * @param sysModule
	 * @return
	 */
	@RequestMapping("updataModule")
	@ResponseBody
	public Object updataModule(SysModule sysModule) {
		String fParentid = sysModule.getParentid();
		Map<String, Object> map = new HashMap<String, Object>();
		String fId = sysModule.getId();
		boolean updataByPrimaryKey = false;
		if (null != fId) {
			updataByPrimaryKey = sysModuleService.updateByPrimaryKeySelective(sysModule);
		}

		if (updataByPrimaryKey) {
			map.put("status", "success");
			map.put("message", "修改成功");
			map.put("data", updataByPrimaryKey);
			return map;
		} else {
			map.put("status", "failure");
			map.put("message", "修改异常");
			map.put("data", updataByPrimaryKey);
			return map;
		}
	}
}
