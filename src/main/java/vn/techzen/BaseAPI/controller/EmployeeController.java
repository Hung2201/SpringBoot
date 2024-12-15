package vn.techzen.BaseAPI.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techzen.BaseAPI.dto.ApiResponse;
import vn.techzen.BaseAPI.dto.Gender;
import vn.techzen.BaseAPI.dto.JsonResponse;
import vn.techzen.BaseAPI.dto.exception.AppException;
import vn.techzen.BaseAPI.dto.exception.ErrorCode;
import vn.techzen.BaseAPI.models.Employee;
import vn.techzen.BaseAPI.service.IEmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/employee")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeController {
    IEmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<?> getAllEmployees() {
        return JsonResponse.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
        Employee empl = employeeService.getEmployee(id);
        if (empl == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        return JsonResponse.ok(empl);
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        return JsonResponse.created(employeeService.addEmployee(emp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable String id, @RequestBody Employee updatedData) {
        Employee updatedEmployee = employeeService.updateEmployee(id, updatedData);

        if (updatedEmployee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        return JsonResponse.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return JsonResponse.noContent();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String dobFrom,
            @RequestParam(required = false) String dobTo,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String salaryRange,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String departmentId) {

        List<Employee> filterEmployee = employeeService.findByAttributes(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);

        if (filterEmployee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        return JsonResponse.ok(filterEmployee);
    }
}