package com.cinemaster.backend;

import com.cinemaster.backend.service.AccountServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountServiceTest.class,
})
class BackendApplicationTests {
}
