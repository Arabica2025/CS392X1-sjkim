/*
 * Payroll class
 * consists of 
 * 
 * 1. a set of Employees (private Employee people[])
 * 
 * 2. Allocate an array of some positive size to *people* (private int maximum_size)
 * 
 * 3. private number of function can DOULBE the size of the payroll as needed (don't worry about decreasing payroll size)
 * 
 * store the number of actuall Employees in the private variable (private int current_size) => people[0] stores the actual Employee number
 *  
 */
public class Payroll {
    // INITIAL_MAXIMUM_SIZE: the default size of the initial array created by some constructor of Payroll class
    public static final int INITIAL_MAXIMUM_SIZE = 1024;

    public Payroll() {
	/* default constructor
     * 
     * 1. allocate default payroll size to the people var
     * 2. set maximum_size appropriately
     * 3. set current_size to 0
     *
     */
        this.people = new Employee[INITIAL_MAXIMUM_SIZE];
        this.maximum_size = INITIAL_MAXIMUM_SIZE;
        this.current_size = 0;
    }

    public int size() {
        // public int size(): return current_size
        return this.current_size;
    }

    public void print() {
        // print the Employees currently on the Payroll, one per line
        for (int i = 0; i < this.current_size; i++) {
            System.out.println(this.people[i].name);
        }
    }

    private void deep_copy_increase_size() {
        /* private void deep_copy()
        
        helper method to DOUBLE the size of the payroll
        */
        Employee[] new_size = new Employee[maximum_size * 2];
        int count = 0;
        while (count < this.current_size) {
            new_size[count] = this.people[count];
            count++;
        }
        this.people = new_size;
    }
    
    public void add_employee(Employee newbie) {
	/* public void add_employee(Employee newbie):
    * add a new Employee to the payroll, making more room on the payroll if necessary
    */
        if (this.current_size >= this.maximum_size) {
            System.out.println("Maximum Array Size Reached. Doubling the size...");
            deep_copy_increase_size();
        }
        this.people[this.current_size] = newbie;
        this.current_size++;
    }

    public void remove_employee(int i) throws EmployeeIndexException {
	/* public void remove_employee(int i) throws EmployeeIndexException
    
    remove Employee object in people[i]
    I chose to replace it with the last Employee in the array
    looks more simple and more efficient
    */
        if (i < 0 || i >= this.current_size){
            throw new EmployeeIndexException("Invalid employee index");
        }
        this.people[i] = this.people[this.maximum_size - 1];
        this.people[this.maximum_size - 1] = null;
        this.current_size--;
    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
	/* your code */
    }

    public void add_payroll(Payroll source) {
	/* your code */
    }

    public void copy_payroll(Payroll source) {
	/* your code */
    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
}
