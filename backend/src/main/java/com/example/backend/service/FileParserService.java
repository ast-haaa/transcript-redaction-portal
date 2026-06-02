package com.example.backend.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileParserService {

    public String extractText(MultipartFile file)
            throws Exception {

        String filename =
                file.getOriginalFilename();

        if(filename == null) {

            throw new Exception(
                    "Invalid file"
            );
        }

        // TXT
        if(filename.endsWith(".txt")) {

            return new String(file.getBytes());
        }

        // DOCX
        else if(filename.endsWith(".docx")) {

            XWPFDocument doc =
                    new XWPFDocument(
                            file.getInputStream()
                    );

            StringBuilder text =
                    new StringBuilder();

            doc.getParagraphs().forEach(
                    p -> text.append(
                            p.getText()
                    ).append("\n")
            );

            return text.toString();
        }

        // PDF
        else if(filename.endsWith(".pdf")) {

            PDDocument pdf =
                    PDDocument.load(
                            file.getInputStream()
                    );

            PDFTextStripper stripper =
                    new PDFTextStripper();

            return stripper.getText(pdf);
        }

        throw new Exception(
                "Unsupported file type"
        );
    }
}