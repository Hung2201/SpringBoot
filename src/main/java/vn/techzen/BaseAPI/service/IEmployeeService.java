package vn.techzen.BaseAPI.service;

import vn.techzen.BaseAPI.dto.employee.EmployeeSearchRequest;
import vn.techzen.BaseAPI.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);
    Employee updateEmployee(int id, Employee updatedData);
    void deleteEmployee(int id);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployee(int id);
    List<Employee> addEmployee(Employee emp);
}