package com.dependency.dependencycheck.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dependency-check")
public class DependencyScanController {

  @GetMapping("/scan/{repo}")
  public String scan(@PathVariable("repo") String repo) {
    System.out.println("repo = " + repo);
    return repo;
  }
}
