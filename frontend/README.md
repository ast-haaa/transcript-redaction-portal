# Transcript Redaction Portal

A full-stack AI-powered web portal that automatically redacts sensitive information from uploaded business call transcripts.

## Features

* Upload TXT, PDF, and DOCX transcripts
* Automatic PII redaction
* Email and phone number masking
* Financial figure masking
* Audit logs with replacement reasons
* AI-generated summaries
* AI-generated key discussion points
* Download redacted transcript
* Copy transcript functionality
* Responsive modern UI

---

## Tech Stack

### Frontend

* React
* Axios
* CSS

### Backend

* Spring Boot
* Java
* REST APIs

### AI

* Google Gemini API

---

## Redaction Strategy

Hybrid redaction approach using:

* Regex-based detection
* Rule-based masking
* AI-generated summarization and key point extraction

---

## Supported File Types

* TXT
* PDF
* DOCX

---

## How to Run

### Backend

```bash
cd backend
set GEMINI_API_KEY=YOUR_KEY
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## API Endpoint

```bash
POST /api/upload
```

Accepts multipart file upload.

---

## Security

* API keys stored using environment variables
* No transcript persistence
* No sensitive data stored in logs

---

## Author

Astha Adhikari
