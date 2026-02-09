package com.example.videols.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.videols.entity.Video;
import com.example.videols.mapper.VideoMapper;
import com.example.videols.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
}
