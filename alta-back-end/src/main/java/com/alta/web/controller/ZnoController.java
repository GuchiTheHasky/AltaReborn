package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.dto.ZnoDto;
import com.alta.facade.MainFacade;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/znos")
public class ZnoController {
    private final MainFacade mainFacade;

    @GetMapping
    @Operation(
            summary = "Get all znos with optional pagination.",
            tags = "Zno")
    public List<ZnoDto> findAll(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) Integer size) {

        return Optional.ofNullable(page)
                .flatMap(p -> Optional.ofNullable(size)
                        .map(s -> PageRequest.of(page, size)))
                .map(mainFacade::findAllZnosPageByPage)
                .orElseGet(mainFacade::findAllZnos);
    }

    @GetMapping("/{id}/tasks")
    @Operation(
            summary = "Get tasks related to a ZNO by ID.",
            tags = "Zno")
    public List<TaskDto> findTasksByZnoId(@PathVariable int id) {
        ZnoDto zno = mainFacade.findZnoById(id);

        return zno.getTasks();
    }

}
