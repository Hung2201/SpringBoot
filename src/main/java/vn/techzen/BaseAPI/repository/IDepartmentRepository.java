package vn.techzen.BaseAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techzen.BaseAPI.entity.Department;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
}
