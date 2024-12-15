package vn.techzen.BaseAPI.service;

import org.springframework.stereotype.Component;
import vn.techzen.BaseAPI.models.Employee;

import java.util.List;


public interface IEmployeeService {
    List<vn.techzen.BaseAPI.models.Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId);
    Void deleteEmployee(String id);
    Employee updateEmployee(String id, Employee updatedData);
    List<Employee> getAllEmployees();
    Employee getEmployee(String id);
    List<Employee> addEmployee(Employee emp);
}
