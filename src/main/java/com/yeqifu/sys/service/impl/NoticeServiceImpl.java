package com.yeqifu.sys.service.impl;

import com.yeqifu.sys.entity.Notice;
import com.yeqifu.sys.mapper.NoticeMapper;
import com.yeqifu.sys.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
