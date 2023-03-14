package org.example.service;

import org.example.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobotDao extends JpaRepository<Robot, Long> {
}
