package com.example.videols.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.videols.entity.LearningProgress;
import com.example.videols.mapper.LearningProgressMapper;
import com.example.videols.service.LearningProgressService;
import org.springframework.stereotype.Service;

@Service
public class LearningProgressServiceImpl extends ServiceImpl<LearningProgressMapper, LearningProgress> implements LearningProgressService {
}
