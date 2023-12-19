package com.alta.service.impl;

import com.alta.dto.TaskDto;
import com.alta.entity.Student;
import com.alta.entity.Task;
import com.alta.exception.TaskException;
import com.alta.exception.WriteZipException;
import com.alta.mapper.TaskMapper;
import com.alta.mapper.TopicMapper;
import com.alta.repository.TaskRepository;
import com.alta.repository.projection.FullTaskProjection;
import com.alta.service.TaskService;
import com.alta.util.DefaultPdfFactory;
import com.alta.web.controller.request.TaskRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultTaskService implements TaskService {

    private static final String PDF_TYPE = ".pdf";
    private static final String PDF_TYPE_WITH_ANSWER = "_WithAnswers.pdf";


    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final TopicMapper topicMapper;
    private final DefaultPdfFactory pdfFactory;


    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(taskMapper::toTaskDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto update(int id, TaskDto taskDto) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setDescription(taskDto.getDescription());
                    task.setAnswer(taskDto.getAnswer());
                    task.setImagePath(taskDto.getImagePath());
                    task.setLevel(taskDto.getLevel());
                    return taskMapper.toTaskDto(taskRepository.save(task));
                })
                .orElseThrow(() -> new TaskException(id));
    }

    @Override
    public TaskDto findById(int id) {
        return taskMapper.toTaskDto(taskRepository.findById(id).orElseThrow(() -> new TaskException(id)));
    }

    @Override
    public void getZipTaskByFilter(ZipOutputStream zipOutputStream,
                                   String fileName,
                                   Integer studentId,
                                   List<TaskRequest> filteredTaskDtoList) {
        String pdfFileName = fileName + PDF_TYPE;
        String pdfFileNameWithAnswers = fileName + PDF_TYPE_WITH_ANSWER;

        String[] fileNameArray = new String[]{pdfFileName, pdfFileNameWithAnswers};

//        List<Integer> taskIdList = filteredTaskDtoList.stream()
//                .flatMap(task -> task.getTaskIds().stream())
//                .toList();

        List<Integer> taskIdList = new ArrayList<>();
        for (TaskRequest taskRequest : filteredTaskDtoList) {
            taskIdList.addAll(taskRequest.getTaskIds());
        }

        List<Task> taskList = new ArrayList<>();

        for (Integer taskId : taskIdList) {
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            optionalTask.ifPresent(taskList::add);
        }

        pdfFactory.createPdfFromTaskList(pdfFileName, taskList);
        pdfFactory.createPdfFromTaskListWithAnswers(pdfFileNameWithAnswers, taskList);
        createZip(zipOutputStream, fileNameArray);
    }

    private void createZip(ZipOutputStream zipOutputStream, String[] fileNameArray) {
        Arrays.stream(fileNameArray).forEach(fileName -> writeFileToZip(zipOutputStream, fileName));
    }

    private void writeFileToZip(ZipOutputStream zipOutputStream, String fileName){
        File file = new File(fileName);
        Exception exception = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            IOUtils.copy(fileInputStream, zipOutputStream);
        } catch (IOException e) {
            exception = e;
            log.error("Problem writing a file to zip", e);
            throw new WriteZipException("There is problem during putting pdf to zip");
        } finally {
            try {
                if (file.exists()) {
                    file.delete();
                }
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                if (exception != null) {
                    exception.addSuppressed(new WriteZipException("There is problem during putting pdf to zip"));
                }
                log.error("Problem when try close ZipEntry", e);
            }
        }
    }
}
