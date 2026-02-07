package com.example.videols.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.dto.AdminLoginRequest;
import com.example.videols.entity.AdminUser;
import com.example.videols.service.AdminUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {
    private final AdminUserService adminUserService;

    public AdminAuthController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/login")
    public ApiResponse<AdminUser> login(@RequestBody AdminLoginRequest request) {
        AdminUser adminUser = adminUserService.getOne(new QueryWrapper<AdminUser>()
                .eq("username", request.getUsername())
                .eq("password", request.getPassword()));
        if (adminUser == null) {
            return ApiResponse.error("invalid credentials");
        }
        return ApiResponse.success(adminUser);
    }
}
