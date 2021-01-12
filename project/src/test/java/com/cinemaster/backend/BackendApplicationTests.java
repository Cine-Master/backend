package com.cinemaster.backend;

import com.cinemaster.backend.data.dto.*;
import com.cinemaster.backend.data.service.*;
import com.cinemaster.backend.service.*;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountServiceTest.class,
        ActorServiceTest.class,
        CategoryServiceTest.class,
        DirectorServiceTest.class,
        BookingServiceTest.class,
})
class BackendApplicationTests {
}
