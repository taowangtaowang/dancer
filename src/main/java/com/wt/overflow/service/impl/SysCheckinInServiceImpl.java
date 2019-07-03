package com.wt.overflow.service.impl;

import com.wt.overflow.bean.SysCheckingIn;
import com.wt.overflow.dao.SysCheckinInMapper;
import com.wt.overflow.service.SysCheckinInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class SysCheckinInServiceImpl implements SysCheckinInService {

	@Autowired
	private SysCheckinInMapper sysCheckinInMapper;

	public Map<String, Object> queryAllCheckInDetail(Date startTime, Date endTime, String userName) {
		//只计算周一到周五的考勤  早9点 晚5.30
		Map<String, Object> resmap= new HashMap<String, Object>();
		Map<String, Object> parmap= new HashMap<String, Object>();
		parmap.put("startTime", startTime);parmap.put("endTime", endTime);parmap.put("userName", userName);
		List<SysCheckingIn> sysCheckinInList = sysCheckinInMapper.queryAllData(parmap);
		int qiandao = 0 ;int chidao = 0 ;int queqin = 0 ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		for (SysCheckingIn sysCheckingIn : sysCheckinInList) {
			try {
				if(sysCheckingIn.getOUT_TIME()==null){//缺勤
					queqin++;
				}else if (sdf.parse(sdf.format(sysCheckingIn.getOUT_TIME())).getTime()>sdf.parse(sdf2.format(sysCheckingIn.getOUT_TIME())+" 09:00:00").getTime()){//迟到
					chidao++;
				}else{//正常签到  <= 09:00:00
					qiandao++;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		//组json返回结果数据
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append("{value:"+qiandao+",name:'签到'},");
		sb.append("{value:"+chidao+", name:'迟到'},");
		sb.append("{value:"+queqin+", name:'缺勤'}");
		sb.append("]");
		resmap.put("date", sb.toString());
		return resmap;
	}

	
	
	public Map<String, Object> queryAlllistDetail(Date startTime, Date endTime, String userName,String typeString) {
		Map<String, Object> resmap= new HashMap<String, Object>();
		Map<String, Object> parmap= new HashMap<String, Object>();
		parmap.put("startTime", startTime);parmap.put("endTime", endTime);parmap.put("userName", userName);
		List<SysCheckingIn> sysCheckinInList = sysCheckinInMapper.queryAllData(parmap);
		
		List<SysCheckingIn> qiandaolist = new ArrayList<SysCheckingIn>();
		List<SysCheckingIn> chidaolist = new ArrayList<SysCheckingIn>();
		List<SysCheckingIn> queqinlist = new ArrayList<SysCheckingIn>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		for (SysCheckingIn sysCheckingIn : sysCheckinInList) {
			try {
				if(sysCheckingIn.getOUT_TIME()==null){//缺勤
					queqinlist.add(sysCheckingIn);
				}else if (sdf.parse(sdf.format(sysCheckingIn.getOUT_TIME())).getTime()>sdf.parse(sdf2.format(sysCheckingIn.getOUT_TIME())+" 09:00:00").getTime()){//迟到
					chidaolist.add(sysCheckingIn);
				}else{//正常签到  <= 09:00:00
					qiandaolist.add(sysCheckingIn);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(typeString.equals("qiandao")){
			resmap.put("result", qiandaolist);
		}else if (typeString.equals("chidao")){
			resmap.put("result", chidaolist);
		}else{
			resmap.put("result", queqinlist);
		}
		return resmap;//返回相应的结果  减少数据传输大小
	}
	
	
}
