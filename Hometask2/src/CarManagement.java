
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarManagement {
    private List<Car> carList;

    public CarManagement() {
        carList = new ArrayList<>();
        // Sample data
        carList.add(new Car(1, "Toyota", "Corolla", 2018, "Red", 20000, "XYZ123"));
        carList.add(new Car(2, "Honda", "Civic", 2015, "Blue", 25000, "ABC456"));
        carList.add(new Car(3, "Toyota", "Camry", 2020, "Black", 30000, "DEF789"));
        // Add more cars as needed
    }

    public void saveCarsByMake(String make) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(make + "_cars.txt"))) {
            for (Car car : carList) {
                if (car.getMake().equalsIgnoreCase(make)) {
                    writer.write(car.toString());
                    writer.newLine();
                }
            }
        }
    }

    public void saveCarsByModelAndYears(String model, int years) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(model + "_cars.txt"))) {
            int currentYear = 2024; // Adjust as necessary
            for (Car car : carList) {
                if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYearOfManufacture() > years)) {
                    writer.write(car.toString());
                    writer.newLine();
                }
            }
        }
    }

    public void saveCarsByYearAndPrice(int year, double price) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(year + "_cars.txt"))) {
            for (Car car : carList) {
                if (car.getYearOfManufacture() == year && car.getPrice() > price) {
                    writer.write(car.toString());
                    writer.newLine();
                }
            }
        }
    }

    public static void main(String[] args) {
        CarManagement cm = new CarManagement();
        Scanner scanner = new Scanner(System.in);
        
        // Example calls (modify as needed)
        try {
            System.out.println("Enter the make of the car to filter:");
            String make = scanner.nextLine();
            cm.saveCarsByMake(make);
            
            System.out.println("Enter the model of the car to filter and years in use:");
            String model = scanner.nextLine();
            int years = scanner.nextInt();
            cm.saveCarsByModelAndYears(model, years);
            
            System.out.println("Enter the year of manufacture and price:");
            int year = scanner.nextInt();
            double price = scanner.nextDouble();
            cm.saveCarsByYearAndPrice(year, price);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

