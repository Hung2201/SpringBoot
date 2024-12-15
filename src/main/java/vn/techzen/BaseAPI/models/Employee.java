package vn.techzen.BaseAPI.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.techzen.BaseAPI.dto.Gender;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Employee {
    UUID id;
    String name;
    LocalDate dob;
    Gender gender;
    Double salary;
    String phone;
    Integer departmentId;
}

