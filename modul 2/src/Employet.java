public class Employet {
    private String employeeName;
    private int  employeeAge;
    private double salary;
    private String jobDescription;
    public Employet(String empName,int age, double empSalary, String jobDesription){
        employeeName = empName;
        employeeAge = age;
        salary = empSalary;
        jobDescription = jobDesription;
    }
    public void e  (){
        System.out.println("Employee Name : " + employeeName);
        System.out.println("age :" + employeeAge);
        System.out.println("Salary : "+ salary);
        System.out.println("job Description : "+ jobDescription);

    }
    public double c(){
        return salary + 12;

    }
    public void s (double rasisePercentage){
        salary += (salary + rasisePercentage / 100);
    }

}