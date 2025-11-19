package com.example.ResumeMatcher.Controller;

import com.example.ResumeMatcher.Service.AiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiServiceController {

  private final AiService aiService;

  public AiServiceController(AiService aiService) {
    this.aiService = aiService;
  }

  @PostMapping("/analyze")
  public String analyze(@RequestParam String resumeText, @RequestParam String jobDescription) {
    return aiService.analyzeWithOllama(resumeText, jobDescription);
  }
}
