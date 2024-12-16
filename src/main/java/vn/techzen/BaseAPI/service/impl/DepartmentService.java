package vn.techzen.BaseAPI.service.impl;

import vn.techzen.BaseAPI.entity.Department;
import vn.techzen.BaseAPI.repository.IDepartmentRepository;
import vn.techzen.BaseAPI.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    @Autowired
    IDepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartment(@PathVariable int id) {
        return departmentRepository.findById(id);
    }

    public Department addDepartment(@RequestBody Department dp) {
        return departmentRepository.save(dp);
    }

    public Department updateDepartment(@PathVariable int id, @RequestBody Department updatedData) {
        Department dp = departmentRepository.findById(id).orElse(null);
        dp.setName(updatedData.getName());
        return departmentRepository.save(dp);
    }

    public void deleteDepartment(@PathVariable int id) {
        departmentRepository.deleteById(id);
    }
}