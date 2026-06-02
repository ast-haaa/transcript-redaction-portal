package com.example.backend.controller;

import com.example.backend.model.ResponseDto;

import com.example.backend.service.AiService;
import com.example.backend.service.FileParserService;
import com.example.backend.service.RedactionService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

@RequestMapping("/api")

@CrossOrigin(origins = "*")

public class UploadController {

    @Autowired
    private FileParserService parserService;

    @Autowired
    private RedactionService redactionService;

    @Autowired
    private AiService aiService;
@PostMapping("/upload")

public ResponseDto upload(
        @RequestParam("file")
        MultipartFile file
) throws Exception {

    if(file.isEmpty()) {

        throw new Exception(
                "File is empty"
        );
    }

    String fileName =
            file.getOriginalFilename();

    if(fileName == null ||
       !(fileName.endsWith(".txt") ||
         fileName.endsWith(".pdf") ||
         fileName.endsWith(".docx"))) {

        throw new Exception(
                "Unsupported file type"
        );
    }

    String text =
            parserService.extractText(file);

    String redacted =
            redactionService.redact(text);

    String summary =
            aiService.generateSummary(redacted);

    String points =
            aiService.generateKeyPoints(redacted);

    return new ResponseDto(
            redacted,
            summary,
            points,
            redactionService.getAuditLogs()
    );
}

}