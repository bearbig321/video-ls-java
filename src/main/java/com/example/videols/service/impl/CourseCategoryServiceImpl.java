package com.example.videols.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.videols.entity.CourseCategory;
import com.example.videols.mapper.CourseCategoryMapper;
import com.example.videols.service.CourseCategoryService;
import org.springframework.stereotype.Service;

@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {
}
