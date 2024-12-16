package vn.techzen.BaseAPI.service;

import vn.techzen.BaseAPI.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> getAllDepartments();

    Optional<Department> getDepartment(int id);

    Department addDepartment(Department dp);

    Department updateDepartment(int id, Department updatedData);
    void deleteDepartment(int id);
}