package com.example.videols.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.entity.Course;
import com.example.videols.entity.CourseCategory;
import com.example.videols.service.CourseCategoryService;
import com.example.videols.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseCategoryService courseCategoryService;
    private final CourseService courseService;

    public CourseController(CourseCategoryService courseCategoryService, CourseService courseService) {
        this.courseCategoryService = courseCategoryService;
        this.courseService = courseService;
    }

    @GetMapping("/category/list")
    public ApiResponse<List<CourseCategory>> categoryList() {
        List<CourseCategory> list = courseCategoryService.list(new QueryWrapper<CourseCategory>()
                .orderByAsc("sort_order"));
        return ApiResponse.success(list);
    }

    @GetMapping("/list")
    public ApiResponse<List<Course>> courseList(@RequestParam(required = false) Long categoryId) {
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>();
        if (categoryId != null) {
            wrapper.eq("category_id", categoryId);
        }
        wrapper.eq("status", 1).orderByDesc("id");
        return ApiResponse.success(courseService.list(wrapper));
    }

    @GetMapping("/{id}")
    public ApiResponse<Course> courseDetail(@PathVariable Long id) {
        return ApiResponse.success(courseService.getById(id));
    }
}
