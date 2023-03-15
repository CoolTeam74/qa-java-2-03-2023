package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tests", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {
  private final TestService testService;

  @Autowired
  public TestController(final TestService testService) {
    this.testService = testService;
  }

  @GetMapping
  public List<User> getAllTests() {
    return testService.findAll();
  }

}

