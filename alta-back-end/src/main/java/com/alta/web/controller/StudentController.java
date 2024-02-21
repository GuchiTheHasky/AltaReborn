package com.alta.web.controller;

import com.alta.dto.StudentDto;
import com.alta.facade.MainFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@RestController
public class StudentController {
    private final MainFacade mainFacade;

    @GetMapping
    @Operation(
            summary = "Get all students.",
            description = "Retrieves a list of all students available in the system.",
            tags = "Student")
    @ApiResponse(
          responseCode = "200",
          description = "Successfully retrieved list of students.",
          content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(schema = @Schema(implementation = StudentDto.class))))
    public List<StudentDto> findAll() {
        return mainFacade.findAllStudents();
    }

    @GetMapping("/paging")
    public List<StudentDto> findAllStudentsPageByPage(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                  @RequestParam(defaultValue = "10") @Min(1) int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return mainFacade.findAllStudentsPageByPage(pageRequest);
    }
}
