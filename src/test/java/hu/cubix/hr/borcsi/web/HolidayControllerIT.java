package hu.cubix.hr.borcsi.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HolidayControllerIT {
    private static final String BASE_URI = "/api/holiday";
    @Autowired
    HolidayController holidayController;
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testFindAll() {

    }
}
