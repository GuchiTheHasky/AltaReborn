package com.alta.web.controller;

import com.alta.dto.ZnoDto;
import com.alta.service.ZnoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/znos")
@RequiredArgsConstructor
@RestController
public class ZnoController {
    private final ZnoService znoService;

    @GetMapping
    public List<ZnoDto> findAll() {
        return znoService.findAll();
    }

    @PostMapping("/save")
    public ZnoDto save(@RequestBody ZnoDto znoDto) {
        return znoService.save(znoDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        znoService.delete(id);
    }

    @PutMapping("/update/{id}")
    public ZnoDto update(@PathVariable("id") int id, @RequestBody ZnoDto znoDto) {
        return znoService.update(id, znoDto);
    }

}
