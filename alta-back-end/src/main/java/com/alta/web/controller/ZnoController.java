package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.dto.ZnoDto;
import com.alta.service.ZnoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/znos")
@RequiredArgsConstructor
@Validated
public class ZnoController {
    private final ZnoService znoService;

    @GetMapping
    @Operation(
            summary = "Get all znos.",
            tags = "Zno")
    public List<ZnoDto> getAll() {
        return znoService.findAll();
    }

    @GetMapping("/{id}/tasks")
    @Operation(
            summary = "Get tasks related to a ZNO by ID.",
            tags = "Zno")
    public  List<TaskDto>  findTasksByZnoId(@PathVariable @Positive int id) {
        return znoService.findTasksByZnoId(id);
    }

}
