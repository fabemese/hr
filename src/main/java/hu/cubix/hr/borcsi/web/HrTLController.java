package hu.cubix.hr.borcsi.web;

import hu.cubix.hr.borcsi.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HrTLController {
    private List<Employee> employees = new ArrayList<>();

    {
        employees.add(
                new Employee(
                        1L, "name", "position", 1000,
                        LocalDateTime.of(2024, 1, 1, 0, 0, 0))
        );
        employees.add(
                new Employee(
                        2L, "name", "position", 2000, LocalDateTime.of(2014, 1, 1, 0, 0, 0))
        );
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/hr")
    public String hr(Map<String, Object> model) {
        model.put("employees", employees);
        model.put("newEmployee", new Employee());
        model.put("editEmployee", new Employee());
        return "employees";
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployee(@PathVariable int id, Map<String, Object> model) {
        Employee employee = employees.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
        model.put("editEmployee", employee);
        return "editEmployee";
    }

    @PostMapping("/editEmployee")
    public String modifyEmployee(Employee employee) {
        employees = employees.stream()
                .map(e -> {
                    if (e.getId() == employee.getId()) {
                        e.setName(employee.getName());
                        e.setPosition(employee.getPosition());
                        e.setSalary(employee.getSalary());
                        e.setEntryDate(employee.getEntryDate());
                    }
                    return e;
                }).collect(Collectors.toList());

        return "redirect:/hr";
    }

    @PostMapping("/delete/{id}")
    public String hrDelete(@PathVariable int id) {
        employees = employees.stream().filter(e -> e.getId() != id).collect(Collectors.toList());
        return "redirect:/hr";
    }

    @PostMapping("/hr")
    public String addEmployee(Employee employee) {
        Employee maxemployee = employees.stream().max(Comparator.comparing(Employee::getId)).orElse(null);
        if (maxemployee == null) {
            employee.setId(1L);
        } else {
            employee.setId(maxemployee.getId() + 1);
        }
        employees.add(employee);
        return "redirect:hr";
    }
}
