package com.example.ResumeMatcher.Controller;

import com.example.ResumeMatcher.Service.AiService;
import com.example.ResumeMatcher.Service.ResumeService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeServiceController {

  @Autowired private ResumeService resumeService;
  @Autowired private AiService aiService;

  /*1.Upload and extract text only
   Use: When we want to just extract text from resume
  POST http://localhost:8080/api/resume/upload
  */
  @PostMapping("/upload")
  public Map<String, Object> uploadResume(@RequestParam("file") MultipartFile file) {
    String text = resumeService.extractTextFromResume(file);

    Map<String, Object> response = new HashMap<>();
    response.put("fileName", file.getOriginalFilename());
    response.put("content", text.substring(0, Math.min(text.length(), 500)));
    response.put("message", "Resume text extracted successfully!");
    return response;
  }

  /*2.Analyze existing text with job description
  Use:When you already have text and want to analyze it
   POST http://localhost:8080/api/resume/upload
  */
  @PostMapping("/analyze")
  public Map<String, Object> analyzeResume(
      @RequestParam String resumeText, @RequestParam String jobDescription) {
    return resumeService.analyzeMatch(resumeText, jobDescription);
  }

  /* 3. Combined upload + AI analysis in one call
  Use: When you want to upload file and get AI analysis in one step
  POST http://localhost:8080/api/resume/upload-and-analyze
  */
  @PostMapping("/upload-and-analyze")
  public Map<String, Object> uploadAndAnalyze(
      @RequestParam("file") MultipartFile file, @RequestParam String jobDescription) {

    String resumeText = resumeService.extractTextFromResume(file);
    // 3. Call AI Service instead of ResumeService for smart analysis
    String aiAnalysis = aiService.analyzeWithOllama(resumeText, jobDescription);
    Map<String, Object> response = new HashMap<>();
    response.put("fileName", file.getOriginalFilename()); /*Adds: {"fileName": "resume.pdf"} */
    response.put(
        "analysis",
        aiAnalysis); /* Adds: {"fileName": "resume.pdf", "analysis": "AI text here..."}*/
    response.put(
        "message",
        "Resume"
            + " analyzed"
            + " successfully"
            + " with"
            + " AI!"); /*Final: {"fileName": "resume.pdf", "analysis": "...", "message": "..."} */
    return response;
  }
}
