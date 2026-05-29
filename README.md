# Car Dealership Management System

A robust Java-based console application designed to manage a car dealership's inventory, sales, and employee performance. This project was developed as part of **Assignment 5** in the Object-Oriented Programming (OOP) course at **SCE (Sami Shamoon College of Engineering)**.

The system demonstrates advanced Java concepts including **Records, Enums, Custom Exception Handling, File I/O, Generics,** and the **ArrayList** framework.

---

## 🚀 Key Features

- **Employee Management:** Track sales performance and automatically calculate dynamic salaries. Includes a custom sorting algorithm based on sales volume.
- **Inventory Tracking:** Manage vehicle data with strict validation (6-digit plates, mileage, and year constraints).
- **Maintenance & Depreciation:** Log repairs using Java Records. The system automatically calculates vehicle price depreciation based on repair types (Body, Engine, Electrical) and enforces safety thresholds using exceptions.
- **Data Persistence:** - Loads data from `Employee.txt` and `CarDealership.txt` at startup.
  - Logs every sale into `SoldCars.txt`.
  - Updates the inventory in `CarDealership.txt` upon exit.
- **Generic Sorting:** Implements a custom static generic method (`sortDescending`) to handle sorting for any class implementing the `Comparable` interface.

---

## 📂 Project Structure

All files are located within the `cardealership` package:

1. **`CarDealership.java`**: The main driver class. Handles the menu logic, file operations, and the generic sorting algorithm.
2. **`Car.java`**: Represents a vehicle. Contains logic for validation, maintenance history, and depreciation.
3. **`Employee.java`**: Represents a staff member. Implements `Comparable` to allow sorting by sales performance.
4. **`MaintenanceRecord.java`**: A Java `record` used for immutable storage of repair events.
5. **`MaintenanceType.java`**: An `enum` defining repair categories and their base costs.
6. **`MenuOption.java`**: An `enum` used to manage main menu selections safely.

---

## 🛠️ Setup & File Configuration

### **Important: File Placement**
For the application to correctly read your data, place `Employee.txt` and `CarDealership.txt` in the **Project Root Directory** (the main folder of your project, NOT inside the `src` folder).

### **Expected Input Formats**

#### 1. `Employee.txt`
Format: `[Name] [ID] [Sales]`
Example:
```text
Israel 123456789 5
Noa 987654321 12
