package com.example.videols.dto;

public class ProgressSaveRequest {
    private Long userId;
    private Long videoId;
    private Integer progressSeconds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getProgressSeconds() {
        return progressSeconds;
    }

    public void setProgressSeconds(Integer progressSeconds) {
        this.progressSeconds = progressSeconds;
    }
}
