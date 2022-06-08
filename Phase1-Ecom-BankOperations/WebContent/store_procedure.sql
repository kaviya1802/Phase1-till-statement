USE banksystem;
CREATE PROCEDURE add_employee(IN ename varchar(50), IN email varchar(50), IN department varchar(50), IN salary decimal(10,2))
INSERT INTO employees (name, email, department, salary) values (ename,email, department, salary);