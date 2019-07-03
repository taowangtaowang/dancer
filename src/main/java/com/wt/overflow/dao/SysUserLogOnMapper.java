package com.wt.overflow.dao;


import com.wt.overflow.bean.SysUserLogOn;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface SysUserLogOnMapper {

	Integer addSysUserPassWord(SysUserLogOn sysUserLogOn);

	Integer updateSysUserPassWord(SysUserLogOn sysUserLogOn);
}