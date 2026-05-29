// Assignment:5
// Author: Liav Lugasi, ID: 213007271

package cardealership;

/**
 * Represents an employee at the dealership.
 */
public class Employee implements Comparable<Employee> {
    private String name;
    private String id;
    private int sales;

    /**
     * Constructor with validation.
     */
    public Employee(String name, String id, int sales) {
        if (name == null || !name.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Employee name must contain only letters.");
        }
        if (id == null || !id.matches("\\d{9}")) {
            throw new IllegalArgumentException("ID must be exactly 9 digits.");
        }
        if (sales < 0) {
            throw new IllegalArgumentException("Sales count cannot be negative.");
        }
        this.name = name;
        this.id = id;
        this.sales = sales;
    }

    // Getters
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    /**
     * Sells a car, updates sales count, and invokes the car's sell method.
     * @param fileName The file to save sold cars.
     * @param car The car to sell.
     */
    public void sellCar(String fileName, Car car) {
        this.sales++;
        car.sellCar(fileName);
    }

    /**
     * Calculates the employee's salary.
     * @return The calculated salary.
     */
    public double calculateSalary() {
        return 6000 + (this.sales * 100);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", Sales: " + sales + ", Salary: " + calculateSalary();
    }

    /**
     * Compares employees based on sales (descending order).
     */
    @Override
    public int compareTo(Employee other) {
        if (this.sales == other.sales) {
            return 0;
        }
        // Return -1 if the *other* employee has more sales (Descending sort)
        return this.sales < other.sales ? 1 : -1;
    }
}
