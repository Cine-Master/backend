package com.cinemaster.backend;

import com.cinemaster.backend.service.AccountServiceTest;
import com.cinemaster.backend.service.ActorServiceTest;
import com.cinemaster.backend.service.CategoryServiceTest;
import com.cinemaster.backend.service.DirectorServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountServiceTest.class,
        ActorServiceTest.class,
        CategoryServiceTest.class,
        DirectorServiceTest.class,
})
class BackendApplicationTests {
}
