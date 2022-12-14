package com.dependency.dependencycheck.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@Service
public class DependencyScanService {
    private static final String SCRIPT_PATH = "scripts/build-code.bat";
    private static final String SCAN_SCRIPT_PATH = "scripts/scan.bat";

    public String scanRepo(String repo) throws Exception {
        String line;
        String projectName = getProjectName(repo).replace(".git", "");
        System.out.println("projectName = " + projectName);
        String s = buildCode(repo, projectName);
        if (!s.equals("success")) {
            return "report failed";
        }
        String reportContent = scanPhase2(projectName);
        return reportContent;
    }

    private String buildCode(String repo, String projectName) throws IOException {
        Process process = Runtime.getRuntime().exec(SCRIPT_PATH + " " + repo + " " + projectName);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
        return "success";
    }

    private String scanPhase2(String projectName) throws IOException {
        File file = new File("repo\\" + projectName + "\\" + projectName);
        Process process = Runtime.getRuntime().exec(SCAN_SCRIPT_PATH + " " + file.getAbsolutePath() + " "+ projectName);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String reportFilePath = null;
        String line;
        while ((line = input.readLine()) != null) {
            System.out.println(line);
            if (line.contains("Writing report to")) {
                String[] split = line.split("report to:");
                reportFilePath = split[1].trim();
                reportFilePath = reportFilePath.replace("\\.\\", "\\");
                System.out.println("reportFilePath = " + reportFilePath);
            }
        }
        input.close();

        if (reportFilePath != null) {
            String fileContent = FileUtils.readFileToString(new File(reportFilePath), Charset.defaultCharset());
            System.out.println("fileContent = " + fileContent);
            cleanUp();
            return fileContent;
        }
        return "Report generation incomplete";
    }

    private void cleanUp() {
        // TODO: Cleanup logic to add
    }

    public String getPrevReport(String repo) {
        String proj = getProjectName(repo).replace(".git", "");
        String prevReport = "";
        String path = "repo/" + proj + "/dependency-check-report.json";
        try {
            prevReport = FileUtils.readFileToString(new File(path), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prevReport;
    }

    private String getProjectName(String repo) {
        String[] split = repo.split("/");
        return split[split.length - 1];
    }
}
