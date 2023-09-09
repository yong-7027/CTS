package Cash;

public class DriverClient {
    public static void main(String[] args) {
        Employee[] empArray = new Employee[3];
        empArray[0] = new Employee("John", 5, 7000);
        empArray[1] = new CommissionEmployee("Jack", 5, 7000, 3000, 10);
        empArray[2] = new Clerk("J", 5, 7000, 5000, 10);

        printElements(empArray);
    }

    public static void printElements(Employee[] empArray) {
        for(Employee emp:empArray) {
            if(emp instanceof CommissionEmployee) {
                System.out.println("Commission Employee");
            }

            else if(emp instanceof Clerk) {
                System.out.println("Clerk");
            }

            System.out.println(emp.toString());
        }
    }

}
