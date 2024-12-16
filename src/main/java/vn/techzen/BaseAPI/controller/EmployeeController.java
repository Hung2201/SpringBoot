package vn.techzen.BaseAPI.controller;

import vn.techzen.BaseAPI.dto.ApiResponse;
import vn.techzen.BaseAPI.dto.employee.EmployeeSearchRequest;
import vn.techzen.BaseAPI.dto.JsonResponse;
import vn.techzen.BaseAPI.dto.exception.AppException;
import vn.techzen.BaseAPI.dto.exception.ErrorCode;
import vn.techzen.BaseAPI.entity.Employee;
import vn.techzen.BaseAPI.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/employee")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeController {
    IEmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<?> getAllEmployees() {
        return JsonResponse.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        Optional<Employee> empl = employeeService.getEmployee(id);
        if (empl.isEmpty()) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        return JsonResponse.ok(empl);
    }

    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp) {
        return JsonResponse.created(employeeService.addEmployee(emp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable int id, @RequestBody Employee updatedData) {
        Employee updatedEmployee = employeeService.updateEmployee(id, updatedData);

        if (updatedEmployee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        return JsonResponse.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return JsonResponse.noContent();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployees(EmployeeSearchRequest employeeSearchRequest) {

        List<Employee> filterEmployee = employeeService.findByAttributes(employeeSearchRequest);

        if (filterEmployee == null) {
            throw new AppException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }

        return JsonResponse.ok(filterEmployee);
    }
}