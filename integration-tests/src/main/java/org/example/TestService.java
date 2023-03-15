package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
  private final UserDao userDao;

  public List<User> findAll() {
    return userDao.findAll();
  }
}
