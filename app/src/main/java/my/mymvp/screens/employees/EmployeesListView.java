package my.mymvp.screens.employees;



import java.util.List;

import my.mymvp.pojo.Employee;


public interface EmployeesListView {
    void showData(List<Employee> employees);
    void showError();
}
