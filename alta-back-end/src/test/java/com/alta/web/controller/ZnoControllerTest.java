package com.alta.web.controller;

import com.alta.dto.TaskDto;
import com.alta.dto.ZnoDto;
import com.alta.service.ZnoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Log4j2
class ZnoControllerTest {

    @Mock
    private ZnoService znoService;

    @InjectMocks
    private ZnoController znoController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Unit test getAll() for ZnoDto, check size and values")
    void getAll_ReturnsListOfZnosDto() {

        log.info("Running getAll_ReturnsListOfZnosDto test");

        List<ZnoDto> znos = new ArrayList<>();
        znos.add(new ZnoDto(1, "ZNO 1", "2022"));
        znos.add(new ZnoDto(2, "ZNO 2", "2023"));

        when(znoService.findAll()).thenReturn(znos);

        List<ZnoDto> result = znoController.getAll();

        verify(znoService).findAll();

        assertEquals(2, result.size());

        assertEquals(znos.get(0).getId(), result.get(0).getId());
        assertEquals(znos.get(0).getName(), result.get(0).getName());
        assertEquals(znos.get(0).getYear(), result.get(0).getYear());

        assertEquals(znos.get(1).getId(), result.get(1).getId());
        assertEquals(znos.get(1).getName(), result.get(1).getName());
        assertEquals(znos.get(1).getYear(), result.get(1).getYear());
    }

    @Test
    @DisplayName("Unit test findTasksByZnoId() for TaskDto, check size and values")
    void findTasksByZnoId_ReturnsListOfTasksDto() {

        log.info("Running findTasksByZnoId_ReturnsListOfTasksDto test");

        int znoId = 1;
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1, "image1.jpg", "easy", "answer1", "Task 1"));
        tasks.add(new TaskDto(2, "image2.jpg", "medium", "answer2", "Task 2"));

        when(znoService.findTasksByZnoId(znoId)).thenReturn(tasks);

        List<TaskDto> result = znoController.findTasksByZnoId(znoId);

        verify(znoService).findTasksByZnoId(znoId);

        assertEquals(2, result.size());

        assertEquals(tasks.get(0).getId(), result.get(0).getId());
        assertEquals(tasks.get(0).getImagePath(), result.get(0).getImagePath());
        assertEquals(tasks.get(0).getLevel(), result.get(0).getLevel());
        assertEquals(tasks.get(0).getAnswer(), result.get(0).getAnswer());
        assertEquals(tasks.get(0).getTitle(), result.get(0).getTitle());

        assertEquals(tasks.get(1).getId(), result.get(1).getId());
        assertEquals(tasks.get(1).getImagePath(), result.get(1).getImagePath());
        assertEquals(tasks.get(1).getLevel(), result.get(1).getLevel());
        assertEquals(tasks.get(1).getAnswer(), result.get(1).getAnswer());
        assertEquals(tasks.get(1).getTitle(), result.get(1).getTitle());
    }


}