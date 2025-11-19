package com.example.ResumeMatcher.Service;

import java.io.IOException;
import java.util.*;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

  private static final List<String> SKILL_KEYWORDS =
      Arrays.asList(
          "java",
          "spring boot",
          "aws",
          "docker",
          "kubernetes",
          "sql",
          "mysql",
          "react",
          "python",
          "api",
          "git",
          "html",
          "css");

  // Extract text from uploaded resume
  public String extractTextFromResume(MultipartFile file) {
    Tika tika = new Tika();
    try {
      return tika.parseToString(file.getInputStream());
    } catch (IOException | TikaException e) {
      throw new RuntimeException("Failed to extract text from resume", e);
    }
  }

  //  Match skills between resume and job description
  public Map<String, Object> analyzeMatch(String resumeText, String jobDescription) {
    List<String> resumeSkills = extractSkills(resumeText);
    List<String> jobSkills = extractSkills(jobDescription);

    List<String> matched = new ArrayList<>();
    List<String> missing = new ArrayList<>();

    for (String skill : jobSkills) {
      if (resumeSkills.contains(skill)) matched.add(skill);
      else missing.add(skill);
    }

    double matchPercent =
        jobSkills.isEmpty() ? 0.0 : (double) matched.size() / jobSkills.size() * 100;

    Map<String, Object> result = new HashMap<>();
    result.put("matchPercent", Math.round(matchPercent));
    result.put("matchedSkills", matched);
    result.put("missingSkills", missing);

    return result;
  }

  // Helper method to extract skills
  private List<String> extractSkills(String text) {
    text = text.toLowerCase();
    List<String> found = new ArrayList<>();
    for (String skill : SKILL_KEYWORDS) {
      if (text.contains(skill)) {
        found.add(skill);
      }
    }
    return found;
  }
}
