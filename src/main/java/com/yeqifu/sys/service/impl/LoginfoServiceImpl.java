package com.yeqifu.sys.service.impl;

import com.yeqifu.sys.entity.Loginfo;
import com.yeqifu.sys.mapper.LoginfoMapper;
import com.yeqifu.sys.service.ILoginfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginfoServiceImpl extends ServiceImpl<LoginfoMapper, Loginfo> implements ILoginfoService {

}
