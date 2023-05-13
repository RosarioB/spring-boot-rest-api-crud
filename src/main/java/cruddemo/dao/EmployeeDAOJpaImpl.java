package cruddemo.dao;

import cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    // define field for entityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        // execute a query and get result list
        List<Employee> employees = query.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int employeeId) {

        //get employee
        Employee employee = entityManager.find(Employee.class, employeeId);

        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        //save employee
        Employee dbEmployee = entityManager.merge(employee);

        // return the db employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int employeeId) {
        // find employee by id
        Employee employee = entityManager.find(Employee.class, employeeId);

        // remove employee
        entityManager.remove(employee);
    }
}
