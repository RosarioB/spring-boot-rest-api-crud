# spring-boot-rest-api-crud
Spring Boot Rest APIs with CRUD for MySQL. Each branch implements different features:

- **basic_crud**: contains the DAO interface EmployeeDAO and its implementation EmployeeDAOJpaImpl. The JPA methods for CRUD operations have been manually developed.
- **crud_spring_data_jpa**: makes use of Spring data JPA (EmployeeRepository) to implement the DAO. So the JPA methods for CRUD operations are auto generated by Spring Data JPA.
- **in_memory_spring_security**: in the class DemoSecurityConfig has been provided basic in memor authentication and authorization with Spring Security.

In each branch the folder **sql-scripts** contains the SQL scripts to be run in MySQL.

NOTE: to make use of Spring dev tools on Intellij in Settings -> Build, Execution, Deployment check Build Project Automatically and in Advanced Settings check Allow auto-make to start even if developed application is currently running.
