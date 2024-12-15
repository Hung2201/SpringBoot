package vn.techzen.BaseAPI.repository.impl;
import vn.techzen.BaseAPI.dto.ApiResponse;
import vn.techzen.BaseAPI.dto.JsonResponse;
import vn.techzen.BaseAPI.dto.exception.AppException;
import vn.techzen.BaseAPI.dto.exception.ErrorCode;
import vn.techzen.BaseAPI.models.Department;
import vn.techzen.BaseAPI.repository.IDepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Repository
public class DepartmentRepository implements IDepartmentRepository {
    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new vn.techzen.BaseAPI.models.Department(1, "Quản lý"),
                    new vn.techzen.BaseAPI.models.Department(2, "Kế toán"),
                    new vn.techzen.BaseAPI.models.Department(3, "Sale Marketing"),
                    new vn.techzen.BaseAPI.models.Department(4, "Sản xuất")
            )
    );

    public List<Department> getAllDepartments() {
        return departments;
    }

    public Department getDepartment(@PathVariable String id) {
        for (Department dp : departments) {
            if (dp.getId() == Integer.parseInt(id)) {
                return dp;
            }
        }
        return null;
    }

    public Department addDepartment(@RequestBody Department dp) {
        dp.setId((int) (Math.random() * 100000));
        departments.add(dp);
        return dp;
    }

    public Department updateDepartment(@PathVariable String id, @RequestBody Department updatedData) {
        Department existingDepartment = departments.stream()
                .filter(dp -> dp.getId() == Integer.parseInt(id))
                .findFirst()
                .orElse(null);

        existingDepartment.setName(updatedData.getName());

        return existingDepartment;
    }

    public Void deleteDepartment(@PathVariable String id) {
        for (Iterator<Department> iterator = departments.iterator(); iterator.hasNext();) {
            Department dp = iterator.next();
            if (dp.getId() == Integer.parseInt(id)) {
                iterator.remove();
            }
        }
        return null;
    }
}