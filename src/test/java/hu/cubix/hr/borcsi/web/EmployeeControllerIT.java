package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
    @Autowired
    EmployeeController employeeController;

    @Autowired
    WebTestClient webTestClient;

    private static final String BASE_URI = "/api/employees";

    @Test
    public void testCreateEmployee() {
        List<EmployeeDto> employeesBefore = getAllEmployees();
        EmployeeDto newEmployeeDto = new EmployeeDto(12L,
                "uj Teszt", "tesz poz",
                1212, LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40));
        createEmployee(newEmployeeDto);

        List<EmployeeDto> employeesAfter = getAllEmployees();
        assertThat(employeesAfter.subList(0, employeesBefore.size()))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(employeesBefore);
        assertThat(employeesAfter.get(employeesAfter.size() - 1))
                .usingRecursiveComparison().isEqualTo(newEmployeeDto);
    }

    @Test
    public void testCreateEmployeeWithEmptyName() {
        EmployeeDto newEmployeeDto = new EmployeeDto(12L,
                "", "tesz poz",
                1212, LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40));

        webTestClient.post().uri(BASE_URI).bodyValue(newEmployeeDto)
                .exchange().expectStatus().isBadRequest();
    }

    @Test
    public void testEditEmployee() {
        List<EmployeeDto> employeesBefore = getAllEmployees();
        EmployeeDto employeeDto = employeesBefore.get(0);
        EmployeeDto newEmployeeDto = new EmployeeDto(employeeDto.getId(),
                "nameuj", "tesz poz",
                1212, LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40));

        webTestClient.post().uri(BASE_URI + "/" + employeeDto.getId()).bodyValue(newEmployeeDto)
                .exchange().expectStatus().isOk();
        List<EmployeeDto> employeesAfter = getAllEmployees();
        EmployeeDto updatedEmployeeDto = employeesAfter.stream().filter(e -> e.getId() == employeeDto.getId()).findFirst().get();

        assertThat(updatedEmployeeDto).usingRecursiveComparison().isEqualTo(newEmployeeDto);
    }

    private void createEmployee(EmployeeDto employeeDto) {
        webTestClient.post().uri(BASE_URI).bodyValue(employeeDto)
                .exchange().expectStatus().isOk();
    }

    private List<EmployeeDto> getAllEmployees() {
        return webTestClient.get().uri(BASE_URI)
                .exchange().expectBodyList(EmployeeDto.class)
                .returnResult().getResponseBody();
    }


}
