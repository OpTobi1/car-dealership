// Assignment:5
// Author: Liav Lugasi, ID: 213007271

package cardealership;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main application class for managing the Car Dealership.
 */
public class CarDealership {

    /**
     * A generic static method to sort an ArrayList in descending order.
     * @param list The list to be sorted.
     * @param <T> The type of elements in the list, must implement Comparable.
     */
    public static <T extends Comparable<T>> void sortDescending(ArrayList<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // To sort descending: if j is smaller than j+1 (compareTo returns < 0), swap them.
                if (list.get(j).compareTo(list.get(j + 1)) < 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();

        // Load Cars from File
        try (Scanner sc = new Scanner(new File("CarDealership.txt"))) {
            while (sc.hasNext()) {
                try {
                    String plate = sc.next();
                    int year = sc.nextInt();
                    String manuf = sc.next();
                    int mileage = sc.nextInt();
                    double price = sc.nextDouble();
                    cars.add(new Car(plate, year, manuf, mileage, price));
                } catch (Exception e) {
                    System.out.println("Warning: Invalid car data skipped.");
                    if (sc.hasNextLine()) sc.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("CarDealership.txt not found. Starting with empty inventory.");
        }

        // Load Employees from File
        try (Scanner sc = new Scanner(new File("Employee.txt"))) {
            while (sc.hasNext()) {
                try {
                    String name = sc.next();
                    String id = sc.next();
                    int sales = sc.nextInt();
                    employees.add(new Employee(name, id, sales));
                } catch (Exception e) {
                    System.out.println("Warning: Invalid employee data skipped.");
                    if (sc.hasNextLine()) sc.nextLine();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Employee.txt not found. Starting with no employees.");
        }

        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Car Dealership Menu ---");
            System.out.println("1. Show Employees");
            System.out.println("2. Show Unsold Cars");
            System.out.println("3. Sell a Car");
            System.out.println("4. Add a Car");
            System.out.println("5. Add Maintenance Record");
            System.out.println("6. Show Car History & Costs");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
                continue;
            }

            MenuOption opt = MenuOption.fromInt(choice);
            if (opt == null) {
                System.out.println("Invalid option.");
                continue;
            }

            switch (opt) {
                case SHOW_EMPLOYEES:
                    sortDescending(employees);
                    for (Employee e : employees) {
                        System.out.println(e);
                    }
                    break;

                case SHOW_UNSOLD_CARS:
                    for (Car c : cars) {
                        System.out.println(c);
                    }
                    break;

                case SELL_CAR:
                    // Select Employee
                    System.out.println("Employees:");
                    for (Employee e : employees) {
                        System.out.println(e.getName() + " - " + e.getId());
                    }
                    Employee selectedEmp = null;
                    while (selectedEmp == null) {
                        System.out.print("Enter Employee ID: ");
                        String id = input.next();
                        for (Employee e : employees) {
                            if (e.getId().equals(id)) selectedEmp = e;
                        }
                        if (selectedEmp == null) {
                            try {
                                throw new Exception("Exception: Employee ID not found.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage() + " Please try again.");
                            }
                        }
                    }

                    // Select Car
                    System.out.println("Available Cars:");
                    for (Car c : cars) {
                        System.out.println(c);
                    }
                    Car selectedCar = null;
                    while (selectedCar == null) {
                        System.out.print("Enter Car Plate: ");
                        String plate = input.next();
                        for (Car c : cars) {
                            if (c.getCarNum().equals(plate)) selectedCar = c;
                        }
                        if (selectedCar == null) {
                            try {
                                throw new Exception("Exception: Car Plate not found.");
                            } catch (Exception e) {
                                System.out.println(e.getMessage() + " Please try again.");
                            }
                        }
                    }

                    // Process Sale
                    try {
                        selectedEmp.sellCar("SoldCars.txt", selectedCar);
                        cars.remove(selectedCar);
                        System.out.println("Car sold successfully!");
                    } catch (Exception e) {
                        System.out.println("Error processing sale: " + e.getMessage());
                    }
                    break;

                case ADD_CAR:
                    System.out.println("Enter Car Details (Plate Year Manufacturer Mileage Price): ");
                    try {
                        String plate = input.next();
                        int year = input.nextInt();
                        String manuf = input.next();
                        int mileage = input.nextInt();
                        double price = input.nextDouble();
                        cars.add(new Car(plate, year, manuf, mileage, price));
                        System.out.println("Car added successfully!");
                    } catch (Exception e) {
                        System.out.println("Warning: Invalid details provided. Car not added.");
                        input.nextLine(); // Clear buffer
                    }
                    break;

                case ADD_MAINTENANCE:
                    System.out.print("Enter Car Plate: ");
                    String mPlate = input.next();
                    Car carToFix = null;
                    for (Car c : cars) {
                        if (c.getCarNum().equals(mPlate)) carToFix = c;
                    }
                    if (carToFix == null) {
                        System.out.println("Car not found.");
                        break;
                    }

                    System.out.println("Maintenance Types:");
                    MaintenanceType[] types = MaintenanceType.values();
                    for (int i = 0; i < types.length; i++) {
                        System.out.println((i + 1) + ". " + types[i]);
                    }
                    System.out.print("Select type (1-4): ");
                    int typeIdx = input.nextInt() - 1;
                    input.nextLine(); // Consume newline

                    if (typeIdx < 0 || typeIdx >= types.length) {
                        System.out.println("Invalid type selected.");
                        break;
                    }

                    System.out.print("Enter Description: ");
                    String desc = input.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String dateStr = input.next();

                    try {
                        LocalDate date = LocalDate.parse(dateStr);
                        MaintenanceRecord record = new MaintenanceRecord(date, types[typeIdx], desc);
                        carToFix.addMaintenance(record);
                        carToFix.calculateDepreciation();
                        System.out.println("Maintenance added and price updated.");
                    } catch (Exception e) {
                        System.out.println("Warning: " + e.getMessage() + " Returning to menu.");
                    }
                    break;

                case SHOW_HISTORY:
                    System.out.print("Enter Car Plate: ");
                    String hPlate = input.next();
                    Car hCar = null;
                    for (Car c : cars) {
                        if (c.getCarNum().equals(hPlate)) hCar = c;
                    }
                    if (hCar == null) {
                        System.out.println("Car not found.");
                        break;
                    }

                    System.out.println("Car Details: " + hCar);
                    int totalCost = 0;
                    System.out.println("Maintenance History:");
                    for (MaintenanceRecord r : hCar.getMaintenanceHistory()) {
                        System.out.println(r);
                        totalCost += r.type().getCost();
                    }
                    System.out.println("Total Cost to Dealership: " + totalCost + " NIS");
                    break;

                case EXIT:
                    // Overwrite file with remaining unsold cars
                    try (PrintWriter out = new PrintWriter("CarDealership.txt")) {
                        for (Car c : cars) {
                            out.println(c.toString());
                        }
                        System.out.println("Inventory saved. Goodbye!");
                    } catch (FileNotFoundException e) {
                        System.out.println("Error saving inventory.");
                    }
                    running = false;
                    break;
            }
        }
        input.close();
    }
}
