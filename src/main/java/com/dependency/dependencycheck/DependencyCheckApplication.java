package com.dependency.dependencycheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class DependencyCheckApplication {

  public static void main(String[] args) {
    SpringApplication.run(DependencyCheckApplication.class, args);
  }

}
