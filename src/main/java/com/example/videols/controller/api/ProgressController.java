package com.example.videols.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.dto.ProgressSaveRequest;
import com.example.videols.entity.LearningProgress;
import com.example.videols.service.LearningProgressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {
    private final LearningProgressService learningProgressService;

    public ProgressController(LearningProgressService learningProgressService) {
        this.learningProgressService = learningProgressService;
    }

    @PostMapping("/save")
    public ApiResponse<LearningProgress> save(@RequestBody ProgressSaveRequest request) {
        QueryWrapper<LearningProgress> wrapper = new QueryWrapper<LearningProgress>()
                .eq("user_id", request.getUserId())
                .eq("video_id", request.getVideoId());
        LearningProgress existing = learningProgressService.getOne(wrapper);
        if (existing == null) {
            LearningProgress progress = new LearningProgress();
            progress.setUserId(request.getUserId());
            progress.setVideoId(request.getVideoId());
            progress.setProgressSeconds(request.getProgressSeconds());
            progress.setUpdatedAt(LocalDateTime.now());
            learningProgressService.save(progress);
            return ApiResponse.success(progress);
        }
        existing.setProgressSeconds(request.getProgressSeconds());
        existing.setUpdatedAt(LocalDateTime.now());
        learningProgressService.updateById(existing);
        return ApiResponse.success(existing);
    }

    @GetMapping("/get")
    public ApiResponse<LearningProgress> get(@RequestParam Long userId, @RequestParam Long videoId) {
        LearningProgress progress = learningProgressService.getOne(new QueryWrapper<LearningProgress>()
                .eq("user_id", userId)
                .eq("video_id", videoId));
        return ApiResponse.success(progress);
    }
}
