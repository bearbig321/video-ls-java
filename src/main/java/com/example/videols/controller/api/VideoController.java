package com.example.videols.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.entity.Video;
import com.example.videols.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/video")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/list")
    public ApiResponse<List<Video>> list(@RequestParam Long courseId) {
        List<Video> list = videoService.list(new QueryWrapper<Video>()
                .eq("course_id", courseId)
                .orderByAsc("sort_order"));
        return ApiResponse.success(list);
    }
}
