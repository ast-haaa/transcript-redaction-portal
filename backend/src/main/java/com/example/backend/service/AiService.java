
package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class AiService {

    public String generateSummary(String text) {

        return """
        This call discussed company financial performance,
        operational growth, hiring updates, client expansion,
        and pending legal matters. Revenue increased
        significantly year-over-year with stable margins.
        The company also discussed hiring plans, burn rate,
        runway, and arbitration exposure.
        """;
    }

    public String generateKeyPoints(String text) {

        return """
        • Revenue increased significantly YoY
        • EBITDA margin remained stable
        • New enterprise hiring completed
        • Burn rate and runway discussed
        • Pending arbitration matter mentioned
        """;
    }
}
