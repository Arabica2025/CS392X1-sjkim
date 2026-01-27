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
        this.people[i] = this.people[this.current_size - 1];
        this.people[this.current_size - 1] = null;
        this.current_size--;
    }
    
    public int find_employee(String target_name) throws EmployeeNotFoundException {
	/* public int find_employee(String name) throws EmployeeNotFoundException
    
    return the first i such that people[i].name == target_name
    throw exception if no such i found
    */
        for (int i =0; i < this.current_size; i++){
            if (this.people[i].name.equals(target_name)){
                return i;
            }
        }
        throw new EmployeeNotFoundException("Employee not found in the database: " + target_name);
    }


    /*helper method to find the longer array between two payrolls
    private int find_maximum_iter(int i_current, int j_current){
        if (i_current < j_current){
            return j_current;
        } else {
            return i_current;
        }
    }
        */

    public void add_payroll(Payroll source) {
	/* combine two Payrolls into one */
    
        Payroll combined = new Payroll();
        combined.maximum_size = this.maximum_size + source.maximum_size;
        combined.current_size = this.current_size + source.current_size;
        combined.people = new Employee[combined.maximum_size];

        for (int i = 0; i < this.current_size; i++){
            combined.people[i] = this.people[i];
            /*
            combined.people[i*2] = this.people[i];
            combined.people[i*2+1] = source.people[i];
            if (this.people[i] == null || source.people[i] != null){
                int currentIndex = i*2;
                combined.people[currentIndex] = source.people[i];

            }
                */
        }
        int midCount = 0;
        for (int j = this.current_size; j < combined.current_size; j++){
            combined.people[j] = source.people[midCount];
            midCount++;
        }
        this.maximum_size = combined.maximum_size;
        this.current_size = combined.current_size;
        this.people = combined.people;
    }

    public void copy_payroll(Payroll source) {
	/* assign one Payroll to another
    
        Payroll has
        maximum_size
        current_size
        people[]
        */

        this.maximum_size = source.maximum_size;
        this.current_size = source.current_size;
        this.people = new Employee[maximum_size];
        for (int i = 0; i < current_size; i++) {
            this.people[i] = source.people[i];
        }
    }

    private Employee people[];
    private int maximum_size;
    private int current_size;
}
