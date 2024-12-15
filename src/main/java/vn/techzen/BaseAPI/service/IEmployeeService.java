package vn.techzen.BaseAPI.service;

import org.springframework.stereotype.Component;
import vn.techzen.BaseAPI.models.Employee;

import java.util.List;
import java.util.UUID;


public interface IEmployeeService {
    List<vn.techzen.BaseAPI.models.Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId);
    Void deleteEmployee(UUID id);
    Employee updateEmployee(UUID id, Employee updatedData);
    List<Employee> getAllEmployees();
    Employee getEmployee(UUID id);
    List<Employee> addEmployee(Employee emp);
}
