package kursovaya20.kursovaya20.controller;

import kursovaya20.kursovaya20.exception.EmployeeAlreadyAddedException;
import kursovaya20.kursovaya20.exception.EmployeeNotFoundException;
import kursovaya20.kursovaya20.model.Employee;
import kursovaya20.kursovaya20.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;

    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    void addEmployee_ValidInput_ReturnsEmployee() {
        String firstName = "John";
        String lastName = "Doe";
        Employee employee = new Employee(firstName, lastName);

        when(employeeService.add(firstName, lastName)).thenReturn(employee);

        Employee result = employeeController.add(firstName, lastName);

        Assertions.assertEquals(employee, result);
        verify(employeeService, times(1)).add(firstName, lastName);
    }

    @Test
    void addEmployee_DuplicateEmployee_ThrowsException() {
        String firstName = "John";
        String lastName = "Doe";

        when(employeeService.add(firstName, lastName)).thenThrow(EmployeeAlreadyAddedException.class);

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeController.add(firstName, lastName);
        });
        verify(employeeService, times(1)).add(firstName, lastName);
    }

    @Test
    void removeEmployee_ValidInput_ReturnsEmployee() {
        String firstName = "John";
        String lastName = "Doe";
        Employee employee = new Employee(firstName, lastName);

        when(employeeService.remove(firstName, lastName)).thenReturn(employee);

        Employee result = employeeController.remove(firstName, lastName);

        Assertions.assertEquals(employee, result);
        verify(employeeService, times(1)).remove(firstName, lastName);
    }

    @Test
    void removeEmployee_NonExistingEmployee_ThrowsException() {
        String firstName = "John";
        String lastName = "Doe";

        when(employeeService.remove(firstName, lastName)).thenThrow(EmployeeNotFoundException.class);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeController.remove(firstName, lastName);
        });
        verify(employeeService, times(1)).remove(firstName, lastName);
    }

    @Test
    void findEmployee_ValidInput_ReturnsEmployee() {
        String firstName = "John";
        String lastName = "Doe";
        Employee employee = new Employee(firstName, lastName);

        when(employeeService.find(firstName, lastName)).thenReturn(employee);

        Employee result = employeeController.find(firstName, lastName);

        Assertions.assertEquals(employee, result);
        verify(employeeService, times(1)).find(firstName, lastName);
    }

    @Test
    void findEmployee_NonExistingEmployee_ThrowsException() {
        String firstName = "John";
        String lastName = "Doe";

        when(employeeService.find(firstName, lastName)).thenThrow(EmployeeNotFoundException.class);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> {
            employeeController.find(firstName, lastName);
        });
        verify(employeeService, times(1)).find(firstName, lastName);
    }

    @Test
    void findAllEmployees_ReturnsAllEmployees() {
        Map<String, Employee> employees = new HashMap<>();
        employees.put("John Doe", new Employee("John", "Doe"));
        employees.put("Jane Smith", new Employee("Jane", "Smith"));

        when(employeeService.findAll()).thenReturn(employees.values());

        Collection<Employee> result = employeeController.findAll();

        Assertions.assertEquals(employees.values(), result);
        verify(employeeService, times(1)).findAll();
    }
}