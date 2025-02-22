package com.utc.api.controller;


import com.utc.api.dto.response.ApiResponse;
import com.utc.api.dto.response.ClassResponse;
import com.utc.api.service.CharacterService;
import com.utc.api.service.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes/")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("{id}")
    public ApiResponse<?> findById(@PathVariable Long id) {
        return ApiResponse
                   .<ClassResponse>builder()
                   .result(classService.findById(id))
                   .build();
    }

    @GetMapping
    public ApiResponse<?> list() {
        return ApiResponse
                   .<List<ClassResponse>>builder()
                   .result(classService.findAll())
                   .build();
    }
}
