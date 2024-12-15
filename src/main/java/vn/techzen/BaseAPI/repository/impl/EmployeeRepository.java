package vn.techzen.BaseAPI.repository.impl;

import vn.techzen.BaseAPI.dto.Gender;
import vn.techzen.BaseAPI.models.Employee;
import vn.techzen.BaseAPI.repository.IEmployeeRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class EmployeeRepository implements IEmployeeRepository {
    private List<Employee> employee = new ArrayList<>(
            Arrays.asList(
                    new vn.techzen.BaseAPI.models.Employee(UUID.randomUUID(), "Trần Đức Hùng", LocalDate.of(2003, 10, 11), Gender.MALE, 20000.0, "123-456-7890", 1),
                    new vn.techzen.BaseAPI.models.Employee(UUID.randomUUID(), "Y Âm", LocalDate.of(2004, 3, 5), Gender.FEMALE, 10000.0, "234-567-8901", 2),
                    new vn.techzen.BaseAPI.models.Employee(UUID.randomUUID(), "Hoàng Hữu Hùng", LocalDate.of(2003, 5, 28), Gender.MALE, 15000.0, "345-678-9012", 2),
                    new vn.techzen.BaseAPI.models.Employee(UUID.randomUUID(), "A Tân", LocalDate.of(2001, 3, 11), Gender.MALE, 4000.0, "456-789-0123", 3),
                    new vn.techzen.BaseAPI.models.Employee(UUID.randomUUID(), "Charlie White", LocalDate.of(1988, 7, 19), Gender.MALE, 30000.0, "567-890-1234", 4)
            )
    );

    public List<Employee> findByAttributes(String name, String dobFrom, String dobTo, String gender, String salaryRange, String phone, String departmentId) {
        final Double salaryFrom;
        final Double salaryTo;

        if (salaryRange != null && salaryRange.matches("\\d+-\\d+")) {
            String[] range = salaryRange.split("-");
            salaryFrom = Double.parseDouble(range[0]);
            salaryTo = Double.parseDouble(range[1]);
        } else {
            salaryFrom = null;
            salaryTo = null;
        }

        final LocalDate parsedDobFrom = dobFrom != null ? LocalDate.parse(dobFrom) : null;
        final LocalDate parsedDobTo = dobTo != null ? LocalDate.parse(dobTo) : null;

        final Integer parsedDepartmentId = departmentId != null ? Integer.parseInt(departmentId) : null;

        List<Employee> filteredEmployees = employee.stream()
                .filter(emp -> name == null || emp.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(emp -> parsedDobFrom == null || !emp.getDob().isBefore(parsedDobFrom))
                .filter(emp -> parsedDobTo == null || !emp.getDob().isAfter(parsedDobTo))
                .filter(emp -> gender == null || !emp.getGender().equals(gender))
                .filter(emp -> salaryFrom == null || emp.getSalary() >= salaryFrom)
                .filter(emp -> salaryTo == null || emp.getSalary() <= salaryTo)
                .filter(emp -> phone == null || emp.getPhone().contains(phone))
                .filter(emp -> parsedDepartmentId == null || emp.getDepartmentId().equals(parsedDepartmentId))
                .toList();

        return filteredEmployees;
    }

    public List<Employee> getAllEmployees() {
        return employee;
    }

    public Employee getEmployee(UUID id) {
        Employee empl = null;
        for (Employee emp : employee) {
            if (emp.getId().equals(id)) {
                empl = emp;
            }
        }
        return empl;
    }

    public List<Employee> addEmployee(Employee emp) {
        emp.setId((UUID.randomUUID()));
        employee.add(emp);
        return employee;
    }

    public Employee updateEmployee(UUID id, Employee updatedData)  {
        Employee existingEmployee = employee.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);

        existingEmployee.setName(updatedData.getName());
        existingEmployee.setDob(updatedData.getDob());
        existingEmployee.setGender(updatedData.getGender());
        existingEmployee.setPhone(updatedData.getPhone());
        existingEmployee.setSalary(updatedData.getSalary());

        return existingEmployee;
    }

    public Void deleteEmployee(UUID id) {
        for (Iterator<Employee> iterator = employee.iterator(); iterator.hasNext();) {
            Employee emp = iterator.next();
            if (emp.getId().equals(id)) {
                iterator.remove();
            }
        }
        return null;
    }
}