package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuditLog {

    private String original;

    private String replacement;

    private String reason;
}