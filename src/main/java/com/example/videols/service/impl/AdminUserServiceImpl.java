package com.example.videols.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.videols.entity.AdminUser;
import com.example.videols.mapper.AdminUserMapper;
import com.example.videols.service.AdminUserService;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
}
