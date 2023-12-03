package com.yeqifu.sys.mapper;

import com.yeqifu.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AopMapper {
    int insertLog(SysLog sysLog);
    List<SysLog> listAllLog();
}
