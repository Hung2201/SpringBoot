package vn.techzen.BaseAPI.service.impl;

import vn.techzen.BaseAPI.dto.employee.EmployeeSearchRequest;
import vn.techzen.BaseAPI.entity.Employee;
import vn.techzen.BaseAPI.repository.IEmployeeRepository;
import vn.techzen.BaseAPI.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(int id, Employee updatedData){
        Employee employee = employeeRepository.findById(id).get();
        employee.setName(updatedData.getName());
        employee.setBirthday(updatedData.getBirthday());
        employee.setSalary(updatedData.getSalary());
        employee.setPhone(updatedData.getPhone());
        employee.setGender(updatedData.getGender());
        employee.setDepartment(updatedData.getDepartment());
        employeeRepository.save(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> addEmployee(Employee emp) {
        return Collections.singletonList(employeeRepository.save(emp));
    }
}