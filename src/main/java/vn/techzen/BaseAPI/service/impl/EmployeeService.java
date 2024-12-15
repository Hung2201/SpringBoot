package vn.techzen.BaseAPI.service.impl;


import vn.techzen.BaseAPI.models.Employee;
import vn.techzen.BaseAPI.repository.IEmployeeRepository;
import vn.techzen.BaseAPI.repository.impl.EmployeeRepository;
import vn.techzen.BaseAPI.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {

    @Autowired
    IEmployeeRepository employeeRepository;

    public List<Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId) {
        return employeeRepository.findByAttributes(name, dobFrom, dobTo, gender, salaryRange, phone, departmentId);
    }

    public Void deleteEmployee(UUID id) {
        return employeeRepository.deleteEmployee(id);
    }

    public Employee updateEmployee(UUID id, Employee updatedData){
        return employeeRepository.updateEmployee(id, updatedData);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployee(UUID id) {
        return employeeRepository.getEmployee(id);
    }

    public List<Employee> addEmployee(Employee emp) {
        return employeeRepository.addEmployee(emp);
    }
}