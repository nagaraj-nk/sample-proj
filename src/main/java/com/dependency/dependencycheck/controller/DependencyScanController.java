package com.dependency.dependencycheck.controller;

import com.dependency.dependencycheck.service.DependencyScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependency-check")
public class DependencyScanController {

  @Autowired
  private DependencyScanService scanService;

  @GetMapping(value = "/scan", produces = MediaType.APPLICATION_JSON_VALUE)
  public String scan(@RequestParam("repo") String repo) {
    System.out.println("repo = " + repo);
    try {
      scanService.scanRepo(repo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return repo;
  }
}
