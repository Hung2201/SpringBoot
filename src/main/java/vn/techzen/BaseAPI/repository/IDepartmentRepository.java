package vn.techzen.BaseAPI.repository;

import vn.techzen.BaseAPI.models.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> getAllDepartments();

    Department getDepartment(String id);

    Department addDepartment(Department dp);

    Department updateDepartment(String id, Department updatedData);
    Void deleteDepartment(String id);
}
