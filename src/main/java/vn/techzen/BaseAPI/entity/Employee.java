package vn.techzen.BaseAPI.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String phone;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "departmentId") // Tên cột khóa ngoại trong bảng Employee
    private Department department;
}