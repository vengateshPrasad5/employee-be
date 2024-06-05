package service;

import com.example.empmanagement.entity.Employee;
import com.example.empmanagement.exception.ResourceNotFoundException;
import com.example.empmanagement.repository.EmployeeRepository;
import com.example.empmanagement.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //to initialize your mocks
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

        employee  = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("johndoe123@gmail.com");
        employee.setId(1L);
    }

    @Test
    public void testGivenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployeeObject(){

        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.empty());

        given(employeeRepository.save(employee)).willReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertThat(savedEmployee).isNotNull();

    }

    @Test
    public void testGivenEmployeeObject_whenSaveEmployee_thenThrowsException(){

        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeService.saveEmployee(employee));

        verify(employeeRepository, never()).save(any(Employee.class));

    }

    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList() {
        Employee employee2 = new Employee();
        employee2.setFirstName("john");
        employee2.setLastName("doe");
        employee2.setEmail("john@gmail.com");
        employee2.setLastName("doe");

        given(employeeRepository.findAll())
                .willReturn(List.of(employee, employee2));

        List<Employee> employeeList = employeeService.getAllEmployees();
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList() {
        given(employeeRepository.findAll())
                .willReturn(Collections.emptyList());
        List<Employee> employeeList = employeeService.getAllEmployees();
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() {
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        Employee savedEmployee = employeeService.getEmployeeById(employee.getId()).get();
        assertThat(savedEmployee).isNotNull();
    }

    //update

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNothing() {
        willDoNothing().given(employeeRepository).deleteById(employee.getId());

//        Mockito.doNothing().when(employeeRepository).deleteById(employee.getId());

        employeeService.deleteEmployee(employee.getId());
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }
}
