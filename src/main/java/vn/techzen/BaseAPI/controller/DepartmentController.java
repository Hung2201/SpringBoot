package vn.techzen.BaseAPI.controller;

import vn.techzen.BaseAPI.dto.ApiResponse;
import vn.techzen.BaseAPI.dto.Gender;
import vn.techzen.BaseAPI.dto.JsonResponse;
import vn.techzen.BaseAPI.dto.exception.AppException;
import vn.techzen.BaseAPI.dto.exception.ErrorCode;
import vn.techzen.BaseAPI.models.Department;
import vn.techzen.BaseAPI.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/department")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<?> getAllDepartments() {
        return JsonResponse.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable String id) {
        Department department = departmentService.getDepartment(id);

        if (department != null) {
            return JsonResponse.ok(department);
        } else {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addDepartment(@RequestBody Department dp) {
        return JsonResponse.created(departmentService.addDepartment(dp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Department>> updateEmployee(@PathVariable String id, @RequestBody Department updatedData) {
        Department department = departmentService.updateDepartment(id, updatedData);

        if (department != null) {
            return JsonResponse.ok(department);
        } else {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return JsonResponse.noContent();
    }
}
