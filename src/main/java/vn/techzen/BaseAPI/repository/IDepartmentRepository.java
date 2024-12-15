package vn.techzen.BaseAPI.repository;

import vn.techzen.BaseAPI.models.Department;

import java.util.List;

public interface IDepartmentRepository {
    List<Department> getDepartments();

    Department findById(int id);

    Department save(Department department);
    void delete(int id);
}
