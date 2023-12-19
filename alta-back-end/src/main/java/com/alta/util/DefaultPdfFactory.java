package com.alta.util;


import com.alta.entity.Answer;
import com.alta.entity.Task;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;

import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfWriter;

import com.alta.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

//@Service
@Component
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DefaultPdfFactory {
    private static final float ADDITION_MARGIN = 100f;
    private static final float CELL_HEIGHT_FOR_TITLE_PARAGRAPH = 30f;
    private static final float CELL_HEIGHT_FOR_ANSWER_CONTENT = 50f;
    private static final float TABLE_WIDTH = 500f;
    private static final float FONT_SIZE = 12f;
    private static final float SYMBOLS_IN_ONE_ROW = 50f;
    private static final float ROW_HEIGHT = 20f;
    private static final String PATH_TO_FONT = "font/arial.ttf";

    private float pageSize;
    private float tableSizeOnCurrentPage;
    private float currentCellHeight;
    private com.itextpdf.layout.Document document;
    private Table table;
//
//    @Autowired
//    private TaskRepository taskRepository;



    public void createPdfFromTaskList(String pathToFile, List<Task> tasks) {
        createPdf(pathToFile, tasks);
    }

    public void createPdfFromTaskListWithAnswers(String pathToFile, List<Task> tasksWithAnswers) {
        createPdfFromTaskList(pathToFile, tasksWithAnswers);
    }

//    public void createPdfFromTaskListWithAnswers(String pathToFile, List<Task> tasksWithAnswers) {
//        createPdfFromTaskList(pathToFile, tasksWithAnswers, true);
//    }

    private void createPdf(String pathToFile, List<Task> tasksWithAnswers) {
        try (PdfWriter writer = new PdfWriter(pathToFile)) {
            tableSizeOnCurrentPage = 0f;
            currentCellHeight = 0f;
            setPdfDocument(writer);

            float[] pointColumnWidths = {TABLE_WIDTH};
            table = new Table(pointColumnWidths);

//            int paragraph = 1;
//            for (Task taskWithAnswer : tasksWithAnswers) {
//                if (!taskWithAnswer.getImagePath().isEmpty()) {
//                    setImageToPdf(taskWithAnswer, paragraph, isAnswer);
//                } else {
//                    setTextToPdf(taskWithAnswer, paragraph, isAnswer);
//                }
//                paragraph++;
//            }
            document.add(table);
            log.info("Added table to pdf document");
            document.close();
            log.info("Closed pdf document");
        } catch (IOException e) {
            log.error("Problem with path to pdf-file", e);
        }
    }

    private void setPdfDocument(com.itextpdf.kernel.pdf.PdfWriter writer) {
        PdfDocument pdfDoc = new PdfDocument(writer);
        document = new com.itextpdf.layout.Document(pdfDoc);
        document.setFontSize(FONT_SIZE);
        pageSize = document.getPdfDocument().getDefaultPageSize().getHeight() - document.getBottomMargin() - document.getTopMargin();
        log.info("Created pdf document with font size {}", FONT_SIZE);
    }

    private Optional<PdfFont> getFont() {
        try {
            URL pathToFont = this.getClass().getClassLoader().getResource(PATH_TO_FONT);
            log.info("Created Arial font");
            return Optional.of(PdfFontFactory.createFont(Objects.requireNonNull(pathToFont).getPath(), "Identity-H"));
        } catch (IOException e) {
            log.error("Problem creating font", e);
        }
        return Optional.empty();
    }

    @SneakyThrows
    private void setImageToPdf(Task taskWithAnswer, int paragraph) {
        Paragraph paragraphTitle = new Paragraph();
        paragraphTitle.add("Task : " + paragraph);
        log.info("Created paragraph title for task {} with image context", paragraph);
        createCell(CELL_HEIGHT_FOR_TITLE_PARAGRAPH, paragraphTitle, null);

        String pathToImage = taskWithAnswer.getImagePath();
        com.itextpdf.io.image.ImageData imageData = ImageDataFactory.create(new URL(pathToImage));
        com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(imageData);
        log.info("Created Image from url {}", pathToImage);

        createCell(image.getImageHeight(), null, image);
//        if (isAnswer) {
//            setAnswerToPdf(taskWithAnswer.getCorrectAnswers());
//        }
    }

    private void setTextToPdf(Task taskWithAnswer, int paragraph) {
        Paragraph paragraphTitle = new Paragraph();
        paragraphTitle.add("Task : " + paragraph);
        log.info("Created paragraph title for task {} with html context", paragraph);
        createCell(CELL_HEIGHT_FOR_TITLE_PARAGRAPH, paragraphTitle, null);

        //org.jsoup.nodes.Document jsoupDocument = Jsoup.parse(taskWithAnswer.getHtmlFormat());
        //String html = jsoupDocument.body().text();
//        Text text = new Text(html);
//        getFont().ifPresent(text::setFont);
//        Paragraph paragraphContent = new Paragraph(text);
//        paragraphContent.add(html);
//        float htmlCellHeight = (float) (Math.ceil(html.length() / SYMBOLS_IN_ONE_ROW) * ROW_HEIGHT);
//        createCell(htmlCellHeight, paragraphContent, null);
//        if (isAnswer) {
//            setAnswerToPdf(taskWithAnswer.getCorrectAnswers());
//        }
    }

//    private void setAnswerToPdf(Set<Answer> answerSet) {
//        Paragraph answerParagraph = new Paragraph();
//        answerParagraph.add("Answer: ");
//        log.info("Created paragraph title for answer");
//        createCell(CELL_HEIGHT_FOR_TITLE_PARAGRAPH, answerParagraph, null);
//
//        for (Answer answer : answerSet) {
//            Text text = new Text(answer.getText());
//            getFont().ifPresent(text::setFont);
//            Paragraph paragraph = new Paragraph(text);
//            createCell(CELL_HEIGHT_FOR_ANSWER_CONTENT, paragraph, null);
//        }
//    }

    private void isNewPage() {
        if (pageSize - tableSizeOnCurrentPage < currentCellHeight) {
            document.add(table);
            log.info("Added table to pdf document");
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            log.info("Created new pdf page");
            float[] pointColumnWidths = {TABLE_WIDTH};
            table = new Table(pointColumnWidths);
            log.info("Created new table");
            tableSizeOnCurrentPage = 0f;
        }
    }

    private void createCell(float cellHeight, Paragraph paragraph, Image image) {
        currentCellHeight = cellHeight + ADDITION_MARGIN;
        isNewPage();
        Cell cell = new Cell();
        cell.setHeight(cellHeight);
        tableSizeOnCurrentPage += cell.getHeight().getValue();
        if (image != null) {
            cell.add(image.setAutoScale(true));
            log.info("Added image to table cell");
        } else {
            cell.add(paragraph);
            log.info("Added paragraph to table cell");
        }
        table.addCell(cell);
        log.info("Added cell to table");
    }
}
