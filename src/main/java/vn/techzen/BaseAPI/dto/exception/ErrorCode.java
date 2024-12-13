package vn.techzen.BaseAPI.dto.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public enum ErrorCode {
    EMPLOYEE_NOT_EXIST(40401, "Employee does not exist", HttpStatus.NOT_FOUND);
    Integer code;
    String message;
    HttpStatus status;
}
