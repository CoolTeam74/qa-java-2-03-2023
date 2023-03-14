package org.example.service;

import org.example.model.Robot;

import java.util.Optional;

public interface RobotDao {
    Optional<Robot> findById(Long id);

    Robot save(Robot entity);
}
