package vn.techzen.BaseAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.BaseAPI.dto.ApiResponse;
import vn.techzen.BaseAPI.dto.Gender;
import vn.techzen.BaseAPI.dto.JsonResponse;
import vn.techzen.BaseAPI.dto.exception.AppException;
import vn.techzen.BaseAPI.dto.exception.ErrorCode;
import vn.techzen.BaseAPI.models.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:5173")
@RequestMapping("/employee")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(UUID.randomUUID(), "Trần Đức Hùng", LocalDate.of(2003, 10, 11), Gender.MALE, 20000.0, "123-456-7890", 1),
            new Employee(UUID.randomUUID(), "Y Âm", LocalDate.of(2004, 3, 5), Gender.FEMALE, 10000.0, "234-567-8901", 2),
            new Employee(UUID.randomUUID(), "Hoàng Hữu Hùng", LocalDate.of(2003, 5, 28), Gender.MALE, 15000.0, "345-678-9012", 2),
            new Employee(UUID.randomUUID(), "A Tân", LocalDate.of(2001, 3, 11), Gender.MALE, 4000.0, "456-789-0123", 3),
            new Employee(UUID.randomUUID(), "Charlie White", LocalDate.of(1988, 7, 19), Gender.MALE, 30000.0, "567-890-1234", 4)
    ));

    // Get all employees
    @GetMapping
    public ResponseEntity<?> getEmployees() {
        return JsonResponse.ok(employees);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dobFrom", required = false) LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) LocalDate dobTo,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "gender", required = false) Gender gender,
            @RequestParam(value = "salary", required = false) Double salary,
            @RequestParam(value = "department", required = false) Integer departmentId) {

        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> (name == null || e.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(e -> (dobFrom == null || !e.getDob().isBefore(dobFrom)))
                .filter(e -> (dobTo == null || !e.getDob().isAfter(dobTo)))
                .filter(e -> (phone == null || e.getPhone().equals(phone)))
                .filter(e -> (gender == null || e.getGender() == gender))
                .filter(e -> (salary == null || e.getSalary().equals(salary)))
                .filter(e -> (departmentId == null || e.getDepartmentId().equals(departmentId)))
                .toList();

        return JsonResponse.ok(filteredEmployees);
    }

    // Get a specific employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }


    // Add a new employee
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());  // Generate a new ID
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    // Update an existing employee by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setDob(employee.getDob());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    e.setDepartmentId(employee.getDepartmentId());

                    return JsonResponse.ok(e);
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") UUID id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return JsonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

}
