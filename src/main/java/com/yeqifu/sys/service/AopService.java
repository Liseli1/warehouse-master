package com.yeqifu.sys.service;

import com.yeqifu.sys.entity.SysLog;

import java.util.List;

public interface AopService {
    int insertLog(SysLog sysLog);
    List<SysLog> listAllLog();
}
