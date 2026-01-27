
public class TestPayroll {
    /* your code */

    public static void main(String[] args){
        // add payroll for unit test
        Payroll payroll = new Payroll();
        Employee emp1 = new Employee("Alice", 1);
        Employee emp2 = new Employee("Bob", 2);

        System.out.println("Welcome to Payroll System!");
        System.out.println();

        // add_employee test
        System.out.println("Initializing employees:");
        payroll.add_employee(emp1);
        payroll.add_employee(emp2);
        payroll.print();
        // current size check
        System.out.println("Current size: " + payroll.size());

        System.out.println();
        // remove_employee test
        System.out.println("Removing employee at index 0:");
        System.out.println("It should remove Alice out of Payroll");
        try {
            payroll.remove_employee(0);
        } catch (EmployeeIndexException e) {
            System.out.println(e.getMessage());
        }
        payroll.print();
        System.out.println();
        System.out.println("Current size: " + payroll.size());

        System.out.println();

        // search Alice and Bob from our Payroll
        System.out.println("Searching for Alice and Bob in the payroll...");
        try {
            int aliceIndex = payroll.find_employee("Alice");
            System.out.println("Found Alice at index " + aliceIndex);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            int bobIndex = payroll.find_employee("Bob");
            System.out.println("Found Bob at index " + bobIndex);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // add_payroll test
        Payroll mergePayroll = new Payroll();
        mergePayroll.add_employee(new Employee("Charles", 5));
        mergePayroll.add_employee(new Employee("Spongebob", 6));
        System.out.println("mergePayroll current list:");
        mergePayroll.print();

        System.out.println();

        System.out.println("Adding mergePayroll to main payroll...");

        payroll.add_payroll(mergePayroll);
        System.out.println();

        System.out.println("Merged payroll: ");
        payroll.print();
        System.out.println("Current payroll size: " + payroll.size());
        System.out.println();


        // copy_payroll test
        Payroll copied = new Payroll();
        Employee emp3 = new Employee("Sungjun", 3);
        Employee emp4 = new Employee("Tim Cook", 4);
        copied.add_employee(emp3);
        copied.add_employee(emp4);
        System.out.println("Employees to be copied: ");
        copied.print();

        payroll.copy_payroll(copied);

        System.out.println();

        System.out.println("Employees trasferred from copied to payroll?");
        System.out.println("It should print Sungjun and Tim Cook if the test passes");
        payroll.print();
        System.out.println("Current payroll size: " + payroll.size());

    }

}
