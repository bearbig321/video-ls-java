package com.example.videols.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.dto.VideoRequest;
import com.example.videols.entity.Video;
import com.example.videols.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/video")
public class AdminVideoController {
    private final VideoService videoService;

    public AdminVideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/add")
    public ApiResponse<Video> add(@RequestBody VideoRequest request) {
        Video video = new Video();
        video.setCourseId(request.getCourseId());
        video.setTitle(request.getTitle());
        video.setPlayUrl(request.getPlayUrl());
        video.setDurationSeconds(request.getDurationSeconds());
        video.setSortOrder(request.getSortOrder());
        videoService.save(video);
        return ApiResponse.success(video);
    }

    @PostMapping("/update")
    public ApiResponse<Video> update(@RequestBody VideoRequest request) {
        Video video = videoService.getById(request.getId());
        if (video == null) {
            return ApiResponse.error("video not found");
        }
        video.setCourseId(request.getCourseId());
        video.setTitle(request.getTitle());
        video.setPlayUrl(request.getPlayUrl());
        video.setDurationSeconds(request.getDurationSeconds());
        video.setSortOrder(request.getSortOrder());
        videoService.updateById(video);
        return ApiResponse.success(video);
    }

    @PostMapping("/delete")
    public ApiResponse<Void> delete(@RequestBody VideoRequest request) {
        videoService.removeById(request.getId());
        return ApiResponse.success();
    }

    @GetMapping("/list")
    public ApiResponse<List<Video>> list(@RequestParam Long courseId) {
        List<Video> list = videoService.list(new QueryWrapper<Video>()
                .eq("course_id", courseId)
                .orderByAsc("sort_order"));
        return ApiResponse.success(list);
    }
}
