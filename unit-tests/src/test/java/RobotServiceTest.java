import org.example.model.Robot;
import org.example.service.RobotDao;
import org.example.service.RobotService;
import org.example.service.RobotServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class RobotServiceTest {
    private RobotDao robotDao;
    private RobotService robotService;

    private static Robot robot;

    @BeforeAll
    public static void prepareTestData() {
        robot = Robot.builder()
                .id(10L)
                .name("Bender")
                .cpu("i7")
                .producer("China")
                .build();
    }

    @BeforeEach
    public void init() {
        robotDao = Mockito.mock(RobotDao.class);
        robotService = new RobotServiceImpl(robotDao);
    }

    @Test
    public void testUpdateRobot() {
        // 1. prepare
        Mockito.when(robotDao.findById(any(Long.class))).thenReturn(Optional.ofNullable(robot));
        Mockito.when(robotDao.save(any(Robot.class))).then(AdditionalAnswers.returnsFirstArg());

        Robot testRobot = Robot.builder()
                .name("Valley")
                .cpu("i9")
                .build();

        // 2. actions
        Robot actualRobot = robotService.update(10L, testRobot);

        // 3. asserts

        Assertions.assertNotNull(actualRobot);

        Assertions.assertEquals(actualRobot.getId(), robot.getId());
        Assertions.assertEquals(actualRobot.getProducer(), robot.getProducer());

        Assertions.assertEquals(actualRobot.getCpu(), testRobot.getCpu());
        Assertions.assertEquals(actualRobot.getName(), testRobot.getName());
    }
}
