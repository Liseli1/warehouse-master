package com.yeqifu.sys.service.impl;

import com.yeqifu.sys.entity.SysLog;
import com.yeqifu.sys.mapper.AopMapper;
import com.yeqifu.sys.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AopServiceImpl implements AopService {
    @Autowired
    private AopMapper aopMapper;
    @Override
    public int insertLog(SysLog sysLog) {
        return aopMapper.insertLog(sysLog);
    }

    @Override
    public List<SysLog> listAllLog() {
        return aopMapper.listAllLog();
    }
}
