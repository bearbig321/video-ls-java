package com.example.videols.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.dto.CategoryRequest;
import com.example.videols.entity.CourseCategory;
import com.example.videols.service.CourseCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController {
    private final CourseCategoryService courseCategoryService;

    public AdminCategoryController(CourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @PostMapping("/add")
    public ApiResponse<CourseCategory> add(@RequestBody CategoryRequest request) {
        CourseCategory category = new CourseCategory();
        category.setName(request.getName());
        category.setSortOrder(request.getSortOrder());
        courseCategoryService.save(category);
        return ApiResponse.success(category);
    }

    @PostMapping("/update")
    public ApiResponse<CourseCategory> update(@RequestBody CategoryRequest request) {
        CourseCategory category = courseCategoryService.getById(request.getId());
        if (category == null) {
            return ApiResponse.error("category not found");
        }
        category.setName(request.getName());
        category.setSortOrder(request.getSortOrder());
        courseCategoryService.updateById(category);
        return ApiResponse.success(category);
    }

    @PostMapping("/delete")
    public ApiResponse<Void> delete(@RequestBody CategoryRequest request) {
        courseCategoryService.removeById(request.getId());
        return ApiResponse.success();
    }

    @GetMapping("/list")
    public ApiResponse<List<CourseCategory>> list() {
        List<CourseCategory> list = courseCategoryService.list(new QueryWrapper<CourseCategory>()
                .orderByAsc("sort_order"));
        return ApiResponse.success(list);
    }
}
