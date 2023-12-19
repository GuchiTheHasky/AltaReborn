package com.alta.web.controller;

import com.alta.service.TaskService;
import com.alta.util.DefaultPdfFactory;
import com.alta.web.controller.request.PdfRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/v1/util")
@RequiredArgsConstructor
public class UtilController {

    private static final String ATTACHMENT = "attachment";

    private final TaskService taskService;


    private final DefaultPdfFactory defaultPdfFactory;


    // Фільтр завдань, який формує ZIP-архів
    @PostMapping(produces = "application/zip")
    // приймає дані, повертає ZIP
    // produces = "application/zip" <- вказує, що цей метод повертає ZIP
    // StreamingResponseBody <- це спеціальний клас, який дозволяє відправляти відповідь потоком.
    public ResponseEntity<StreamingResponseBody> getFilteredTasks(
            @RequestBody PdfRequest pdfRequest) {

        String fileName = pdfRequest.getFileName();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT + "; filename=" + fileName + ".zip")
                .body(out -> writeZipToResponse(pdfRequest, fileName));
    }




    private void writeZipToResponse(PdfRequest tasksSpecificStudentDto, String fileName) throws IOException {
        // Отримайте шлях до кореня проекту
        String projectRoot = System.getProperty("user.dir");

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(projectRoot, fileName + ".zip")))) {
            taskService.getZipTaskByFilter(zipOutputStream, fileName, tasksSpecificStudentDto.getStudentId(), tasksSpecificStudentDto.getFilteredTaskDto());
        }
    }
}
