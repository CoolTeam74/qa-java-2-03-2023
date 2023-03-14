package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Robot;

@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {
  private final RobotDao robotDao;

  @Override
  public Robot update(Long id, Robot robot) {
    Robot found = robotDao.findById(id).orElseGet(null);
    return robotDao.save(Robot.builder()
                .id(id)
                .name(robot.getName() != null ? robot.getName() : found.getName())
                .cpu(robot.getCpu() != null ? robot.getCpu() : found.getCpu())
                .producer(robot.getProducer() != null ? robot.getProducer() : found.getProducer())
                .build());
  }
}
