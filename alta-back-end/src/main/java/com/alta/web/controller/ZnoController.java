package com.alta.web.controller;

import com.alta.entity.Zno;
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
    public List<Zno> findAll() {
       return znoService.findAll();
    }

    @PostMapping("/save")
    public Zno save(@RequestBody Zno zno){
       return znoService.save(zno);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        znoService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Zno update(@PathVariable("id") int id, @RequestBody Zno zno) {
       return znoService.update(id, zno);
    }

 }
