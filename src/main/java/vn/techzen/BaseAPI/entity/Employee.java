package vn.techzen.BaseAPI.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.BaseAPI.dto.Gender;

import java.time.LocalDate;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    LocalDate birthday;
    double salary;
    Gender gender;
    String phone;


    @ManyToOne
    @JoinColumn
    Department department;
}