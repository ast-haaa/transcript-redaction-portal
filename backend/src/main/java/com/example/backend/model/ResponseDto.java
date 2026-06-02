package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String redactedText;

    private String summary;

    private String keyPoints;

    private List<AuditLog> auditLogs;
}