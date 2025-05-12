import java.util.*;

public class GetEmployeeImportance {
    
}

/*
 * Intuitions :
 
    1. We have given and List of Employee information like
        - Unique Id
        - Importance value'
        - Direct subordinates Id
    2. our goal is to find total importance of the target id and all their direct indirect subordinates


 * Pattern :
 
    1. subordinates means neighbors we can say
    2. id is like index of employee
    3. we need to add manager's imp + his subordinate's imp
    4. it's an directed graph means it will not go to manager again
    5. let's consider the id we need to check is a manager 
    6. if it has any subordinates will check them and add their importance 
        if not will return it's importance
    
 
 * Pseudo Code :
 
    -> Globally Declare 
        totalImportance 

    function getEmployeeImportance(employees, id) {
    
        totalImportance = 0

        ! EmployeeID and index is different so we need to find index first
        for(i = 0 to employees.length)
            
            if(employees[i].id == id)
                employeeArrayIndex = i
        
        dfs(id, employeeArrayIndex, employees)
    
    }

    function dfs(currEmployeeId, employeeArrayIndex, employees) {
    
        -> find curr Importance
            currImportance = employees[employeeArrayIndex].importance

        -> add curr employees importance in totalImportance
            totalImportance += currImportance

        -> Get subordinates of that employee
            suborditnatesOfCurrEmployee = employees[employeeArrayIndex].subordinates
        
        -> Check it's subordinates if it has any
            if(suborditnatesOfCurrEmployee.size() != 0)

                -> for(subordinateId : suborditnatesOfCurrEmployee)

                    ! EmployeeID and index is different so we need to find index first
                    for(i = 0 to employees.length)
                        
                        if(employees[i].id == subordinateId)
                            subordinateArrayIndex = i

                    dfs(subordinateId, subordinateArrayIndex, employees)

        return
    }

 
 */