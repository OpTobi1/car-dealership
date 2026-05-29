// Assignment:5
// Author: Liav Lugasi, ID: 213007271

package cardealership;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Represents a car in the dealership.
 */
public class Car {
    private String carNum;
    private int year;
    private String manu;
    private int km;
    private double price;
    private ArrayList<MaintenanceRecord>  History;

    /**
     * Constructor with validation for Car attributes.
     */
    public Car(String carNum, int year, String manu, int km, double price){
        if (carNum == null || !carNum.matches("\\d{6}")) {
            throw new IllegalArgumentException("Car number must be exactly 6 digits.");
        }
        if (year < 2017) {
            throw new IllegalArgumentException("Manufacture year must be 2017 or later.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be strictly positive.");
        }
        if (km < 0) {
            throw new IllegalArgumentException("Mileage cannot be negative.");
        }

        this.carNum = carNum;
        this.year = year;
        this.manu = manu;
        this.km = km;
        this.price = price;
        this.History = new ArrayList<>();
    }

    //Getters
    public String getCarNum() {
        return carNum;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<MaintenanceRecord> getMaintenanceHistory() {
        return History;
    }
    /**
     * Adds a maintenance record to the car's history.
     * @param record The maintenance record to add.
     */
    public void addMaintenance(MaintenanceRecord record){
        this.History.add(record);
    }

    /**
     * Calculates depreciation based on maintenance history and updates the price.
     * Throws an exception if depreciation exceeds 5000 or makes price <= 0.
     */
    public void calculateDepreciation() {
        double totalDepreciation = 0;

        for (MaintenanceRecord record : History) {
            switch (record.type()) {
                case ENGINE_REPAIR -> totalDepreciation += 3000;
                case BODY_REPAIR -> totalDepreciation += 1500;
                case ELECTRICAL -> totalDepreciation += 500;
                case ROUTINE_SERVICE -> totalDepreciation += 0;
            }
        }

        if (totalDepreciation > 5000) {
            throw new IllegalStateException("Depreciation exceeds the maximum allowed 5000 ILS limit.");
        }

        double newPrice = this.price - totalDepreciation;
        if (newPrice <= 0) {
            throw new IllegalStateException("Depreciation causes the car price to become zero or negative.");
        }

        this.price = newPrice;
    }

    /**
     * Appends the car's details to the sold cars file.
     * @param fileName The name of the file.
     */
    public void sellCar(String fileName) {
        // Using FileWriter with 'true' to append to the end of the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(this.toString());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return carNum + " " + year + " " + manu + " " + km + " " + price;
    }
}
