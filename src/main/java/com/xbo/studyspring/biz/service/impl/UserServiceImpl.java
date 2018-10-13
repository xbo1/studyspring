package com.xbo.studyspring.biz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbo.studyspring.biz.entity.User;
import com.xbo.studyspring.biz.mapper.UserMapper;
import com.xbo.studyspring.biz.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
