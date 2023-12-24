package com.alta.web.controller;

import com.alta.dto.ZnoDto;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/znos")
@RequiredArgsConstructor
@RestController
public class ZnoController {
    private final ZnoService znoService;

    @GetMapping
    public List<ZnoDto> findAll() {
        return znoService.findAll();
    }

}
