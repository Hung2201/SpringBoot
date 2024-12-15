package vn.techzen.BaseAPI.service;

import vn.techzen.BaseAPI.models.Department;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAllDepartments();

    Department getDepartment(String id);

    Department addDepartment(Department dp);

    Department updateDepartment(String id, Department updatedData);
    Void deleteDepartment(String id);
}
