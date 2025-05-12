import java.util.*;

public class GetEmployeeImportance {
    
    // Definition for Employee.
    private static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };


    // Globally Declare Variables
    int totalImportance;
    Map<Integer, Integer> originalIndexMap;

    // Driver Function
    public int getImportance(List<Employee> employees, int id) {
        
        int n = employees.size();

        // Assign Value to global variables
        totalImportance = 0;
        originalIndexMap = new HashMap<>();

        // Adding index id and employee unique id in map
        for (int i = 0; i < n; i++) {
            
            originalIndexMap.put(employees.get(i).id, i);
        }

        System.out.println("Index Map : " + originalIndexMap);

        return 0;
    }

    // Recursion Function : To get importance of subordinates
    private void dfs(int currEmployeeId, int currEmployeeArrayIndex, List<Employee> employee) {

        return;
    }

    // Helper Function : To create Employee objects
    private static Employee createEmployee(int id, int importance, Integer... subs) {
        Employee emp = new Employee();
        emp.id = id;
        emp.importance = importance;
        emp.subordinates = Arrays.asList(subs);
        return emp;
    }
    
    public static void main(String[] args) {

        GetEmployeeImportance solution = new GetEmployeeImportance();

        // Test Case 1
        List<Employee> employees1 = Arrays.asList(
            createEmployee(1, 5, 2, 3),
            createEmployee(2, 3),
            createEmployee(3, 3)
        );
        System.out.println("Result 1: " + solution.getImportance(employees1, 1) + "\n");

        // Test Case 2
        List<Employee> employees2 = Arrays.asList(
            createEmployee(1, 2, 5),
            createEmployee(5, -3)
        );
        System.out.println("Result 2: " + solution.getImportance(employees2, 5) + "\n");
    }

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
    
    Improvements :

    1. Changing below lines to get ArrayIndex of the employeeId or subordinateId
        ! EmployeeID and index is different so we need to find index first
        for(i = 0 to employees.length)
            
            if(employees[i].id == id)
                employeeArrayIndex = i
    
 
 * Pseudo Code :
 
    -> Globally Declare 
        totalImportance 

    function getEmployeeImportance(employees, id) {
    
        totalImportance = 0

        -> create a hashmap to store employee ID and it's original index so that we don't have to always use for loop to get it
            Map<Integer, Integer> map = new HashMap<>()  

        -> Add arrayIndex and id in map
            for(i = 0 to employees.length)
                map.put(id, i)

        employeeArrayIndex = map.get(id)

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

                    subordinateArrayIndex = map.get(subordinateId)
                    dfs(subordinateId, subordinateArrayIndex, employees)

        return
    }

 
 */