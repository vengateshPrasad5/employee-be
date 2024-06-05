package repository;

import com.example.empmanagement.entity.Employee;
import com.example.empmanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

//    @Mock
    private Employee employee;
    @Autowired
    private EmployeeRepository employeeRepository;


    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this); //to initialize your mocks

        employee  = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe123@gmail.com");

    }


    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        Employee savedEmployee = employeeRepository.save(employee);

        //assert response
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void givenEmployeeList_whenFindAll_thenReturnEmployeeList(){

        Employee employee1 = new Employee();

        employee1  = new Employee();
        employee1.setFirstName("Peter");
        employee1.setLastName("Parker");
        employee1.setEmail("peterparker765@gmail.com");

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        List<Employee> employeeList = employeeRepository.findAll();

        //assert
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);


    }

    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        Employee savedEmployee = employeeRepository.save(employee);
        Employee employeeDb = employeeRepository.findById(savedEmployee.getId()).get();
        //checking -> assertion
        assertThat(employeeDb).isNotNull();
    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObject() {
        Employee savedEmployee = employeeRepository.save(employee);
        Employee employeeDb = employeeRepository.findById(savedEmployee.getId()).get();
        employeeDb.setEmail("john_doe@gmail.com");
        Employee updatedEmployee = employeeRepository.save(employeeDb);
        //checking -> assertion
        assertThat(updatedEmployee.getEmail()).isEqualTo("john_doe@gmail.com");
    }

    @Test
    public void givenEmployeeObject_whenDeleteEmployee_thenRemoveEmployee() {
        Employee savedEmployee = employeeRepository.save(employee);
        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> employeeOptional = employeeRepository.findById(savedEmployee.getId());
        assertThat(employeeOptional).isEmpty();
    }

}
