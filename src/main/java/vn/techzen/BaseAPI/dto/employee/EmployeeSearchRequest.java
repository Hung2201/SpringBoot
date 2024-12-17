package vn.techzen.BaseAPI.dto.employee;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {

    String name;
    LocalDate dobFrom;
    LocalDate dobTo;
    String gender;
    Double minSalary;
    Double maxSalary;
    Integer department;
}
