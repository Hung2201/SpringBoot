package vn.techzen.BaseAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.BaseAPI.dto.Gender;
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
            new Employee(UUID.randomUUID(), "Trần Đức Hùng", LocalDate.of(2003, 10, 11), Gender.MALE, 55000.0, "123-456-7890"),
            new Employee(UUID.randomUUID(), "Y Âm", LocalDate.of(2004, 3, 5), Gender.FEMALE, 60000.0, "234-567-8901"),
            new Employee(UUID.randomUUID(), "Hoàng Hữu Hùng", LocalDate.of(2003, 5, 28), Gender.MALE, 65000.0, "345-678-9012"),
            new Employee(UUID.randomUUID(), "A Tân", LocalDate.of(2001, 3, 11), Gender.MALE, 50000.0, "456-789-0123"),
            new Employee(UUID.randomUUID(), "Charlie White", LocalDate.of(1988, 7, 19), Gender.MALE, 70000.0, "567-890-1234")
    ));

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employees);
    }

    // Get a specific employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable UUID id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                // Return 200 OK with the employee
                return ResponseEntity.ok(employee);
            }
        }
        // Return 404 Not Found if no match is found
        return ResponseEntity.notFound().build();
    }


    // Add a new employee
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());  // Generate a new ID
        employees.add(employee);
        return ResponseEntity.ok(employee);
    }

    // Update an existing employee by ID
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable UUID id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOpt = employees.stream().filter(e -> e.getId().equals(id)).findFirst();

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            // Update fields (you can add more fields to be updated as needed)
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setDob(updatedEmployee.getDob());
            existingEmployee.setGender(updatedEmployee.getGender());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setPhone(updatedEmployee.getPhone());
            return ResponseEntity.ok(existingEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                employees.remove(employee);
                return ResponseEntity.ok("Employee with ID " + id + " has been successfully deleted.");
            }
        }
        return ResponseEntity.notFound().build();
    }

}
