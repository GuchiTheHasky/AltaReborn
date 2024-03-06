package com.alta.web.controller;

import com.alta.dto.FullZnoDto;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/znos")
@RequiredArgsConstructor
public class ZnoController {
    private final ZnoService znoService;

    @GetMapping
    public List<FullZnoDto> getAll() {
        return znoService.findAll();
    }

    @GetMapping("/{id}/tasks")
    public FullZnoDto findById(@PathVariable("id") int id) {
        return znoService.findById(id);
    }

}
