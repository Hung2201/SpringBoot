package vn.techzen.BaseAPI.service.impl;

import vn.techzen.BaseAPI.dto.exception.AppException;
import vn.techzen.BaseAPI.dto.exception.ErrorCode;
import vn.techzen.BaseAPI.models.Department;
import vn.techzen.BaseAPI.repository.IDepartmentRepository;
import vn.techzen.BaseAPI.repository.IEmployeeRepository;
import vn.techzen.BaseAPI.repository.impl.DepartmentRepository;
import vn.techzen.BaseAPI.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    @Autowired
    IDepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Department getDepartment(@PathVariable String id) {
        return departmentRepository.getDepartment(id);
    }

    public Department addDepartment(@RequestBody Department dp) {
        return departmentRepository.addDepartment(dp);
    }

    public Department updateDepartment(@PathVariable String id, @RequestBody Department updatedData) {
        return departmentRepository.updateDepartment(id, updatedData);
    }

    public Void deleteDepartment(@PathVariable String id) {
        return departmentRepository.deleteDepartment(id);
    }
}
