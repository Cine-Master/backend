package com.cinemaster.backend;

import com.cinemaster.backend.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ActorServiceTest.class,
        AdminServiceTest.class,
        CategoryServiceTest.class,
        DirectorServiceTest.class,
        RoomServiceTest.class,
        ShowServiceTest.class,
})
class BackendApplicationTests {
}
