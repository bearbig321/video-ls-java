package com.example.videols.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.videols.common.ApiResponse;
import com.example.videols.dto.CourseRequest;
import com.example.videols.entity.Course;
import com.example.videols.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/course")
public class AdminCourseController {
    private final CourseService courseService;

    public AdminCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ApiResponse<Course> add(@RequestBody CourseRequest request) {
        Course course = new Course();
        course.setCategoryId(request.getCategoryId());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCoverUrl(request.getCoverUrl());
        course.setStatus(request.getStatus());
        courseService.save(course);
        return ApiResponse.success(course);
    }

    @PostMapping("/update")
    public ApiResponse<Course> update(@RequestBody CourseRequest request) {
        Course course = courseService.getById(request.getId());
        if (course == null) {
            return ApiResponse.error("course not found");
        }
        course.setCategoryId(request.getCategoryId());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setCoverUrl(request.getCoverUrl());
        course.setStatus(request.getStatus());
        courseService.updateById(course);
        return ApiResponse.success(course);
    }

    @PostMapping("/delete")
    public ApiResponse<Void> delete(@RequestBody CourseRequest request) {
        courseService.removeById(request.getId());
        return ApiResponse.success();
    }

    @GetMapping("/page")
    public ApiResponse<Page<Course>> page(@RequestParam(defaultValue = "1") long page,
                                          @RequestParam(defaultValue = "10") long size,
                                          @RequestParam(required = false) Long categoryId,
                                          @RequestParam(required = false) Integer status) {
        Page<Course> pager = new Page<Course>(page, size);
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        if (status != null) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("id");
        return ApiResponse.success(courseService.page(pager, wrapper));
    }
}
