package com.example.videols.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.videols.common.ApiResponse;
import com.example.videols.dto.UserLoginRequest;
import com.example.videols.dto.UserRegisterRequest;
import com.example.videols.entity.User;
import com.example.videols.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody UserRegisterRequest request) {
        User existing = userService.getOne(new QueryWrapper<User>().eq("username", request.getUsername()));
        if (existing != null) {
            return ApiResponse.error("username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        userService.save(user);
        return ApiResponse.success(user);
    }

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody UserLoginRequest request) {
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", request.getUsername())
                .eq("password", request.getPassword()));
        if (user == null) {
            return ApiResponse.error("invalid credentials");
        }
        return ApiResponse.success(user);
    }
}
