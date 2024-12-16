CREATE TABLE employee
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    birthday      date NULL,
    gender        VARCHAR(255) NULL,
    phone         VARCHAR(255) NULL,
    salary DOUBLE NOT NULL,
    department_id INT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_DEPARTMENTID FOREIGN KEY (department_id) REFERENCES department (id);