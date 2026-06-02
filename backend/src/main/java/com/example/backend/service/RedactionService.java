package com.example.backend.service;

import com.example.backend.model.AuditLog;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RedactionService {

    private final List<AuditLog> auditLogs =
            new ArrayList<>();

    public String redact(String text) {

        auditLogs.clear();

        // EMAIL
        text = replacePattern(
                text,
                "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+",
                "[EMAIL]",
                "Email detected"
        );

        // PHONE
        text = replacePattern(
                text,
                "\\+?\\d[\\d -]{8,}\\d",
                "[PHONE_NUMBER]",
                "Phone number detected"
        );

        // MONEY
        text = replacePattern(
                text,
                "₹\\s?\\d+[\\d.,]*\\s?(crores|lakhs)?",
                "[FINANCIAL_FIGURE]",
                "Financial figure detected"
        );

        // PERSON NAME
        text = replacePattern(
                text,
                "\\b([A-Z][a-z]+\\s[A-Z][a-z]+)\\b",
                "[PERSON_NAME]",
                "Possible person name"
        );

        return text;
    }

    private String replacePattern(
            String text,
            String regex,
            String replacement,
            String reason
    ) {

        Pattern pattern =
                Pattern.compile(regex);

        Matcher matcher =
                pattern.matcher(text);

        while(matcher.find()) {

            auditLogs.add(
                    new AuditLog(
                            matcher.group(),
                            replacement,
                            reason
                    )
            );
        }

        return text.replaceAll(
                regex,
                replacement
        );
    }

    public List<AuditLog> getAuditLogs() {

        return auditLogs;
    }
}