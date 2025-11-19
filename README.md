
## Resume-Analyzer Using AI 
Automatically analyzes resumes against job descriptions to calculate skill match percentage, identify missing competencies, and streamline recruitment screening processes. 
## AI-powered Resume Analyzer using Spring Boot + Ollama + Apache Tika 
This project analyzes a user’s resume vs job description and generates: 
 1. Skill match percentage
 2. Missing skills 
 3. Matched skills 
 4. AI-based explanation using LLM 
 5. Local LLM (Ollama) – No API keys 
 6. Docker support for deployment 
 
 ## Tech Stack & Tools Used -Backend 
 -Java 17 
 -Spring Boot 3 
 -Spring Web 
 -Apache Tika → Extract text from PDF, DOCX, TXT -REST API 
 
 ## AI / LLM -Ollama (local LLM server) 
 -Model: llama3:latest 
 # LLM used for: 
 -Smart skill extraction 
 -Resume summarization 
 -Structured job-resume comparison 
 
 ## DevOps / Deployment 
 -Git & GitHub 
 -Docker 
 
 ## Features Implemented 
 -Upload Resume -Supports: PDF, DOCX, TXT -Extract Text 
 -Apache Tika automatically extracts clean text. 
 
 ## Match Resume with Job Description 
 -Skill matching 
 -Percentage calculation 
 -Missing vs matched skills 
 
 ## LLM Enhancement (Ollama) 
 -Local AI processing 
 -No API keys, no cloud cost 
 -Generates accurate skill analysis 
 
 ## How to Run Project Locally 
 -Start Ollama 
 -ollama run llama3 
 
 ## Run Spring Boot app 
 - mvn spring-boot:run 
 
 ## Test API in Postman 
 - Endpoints provided in ResumeController
