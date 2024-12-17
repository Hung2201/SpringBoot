package vn.techzen.BaseAPI.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.techzen.BaseAPI.entity.Employee;
import vn.techzen.BaseAPI.dto.Gender;

import java.time.LocalDate;
import java.util.List;

public interface IEmployeeService {
    Page<Employee> findAll(String name, LocalDate dobFrom, LocalDate dobTo, Gender gender, Integer salaryRange, String phone, Integer department_id, Pageable pageable);
    Employee findById(int id);
    Employee save(Employee employee);
    Employee update(Employee employee,int id);
    Void deleteEmployee(int id);
}