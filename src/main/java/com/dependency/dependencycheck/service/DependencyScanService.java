package com.dependency.dependencycheck.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class DependencyScanService {
  private static final String SCRIPT_PATH = "scripts/scan.bat";

  public void scanRepo(String repo) throws Exception {
    String line;
    String projectName = getProjectName(repo);
    System.out.println("projectName = " + projectName);
    Process process = Runtime.getRuntime().exec(SCRIPT_PATH + " " + repo + " " + projectName);
    BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
    while ((line = input.readLine()) != null) {
      System.out.println(line);
    }
    input.close();
  }

  private String getProjectName(String repo) {
    String[] split = repo.split("/");
    return split[split.length-1];
  }
}
