package studentsapp.model;

import studentsapp.exceptions.InvalidAgeException;
import studentsapp.exceptions.InvalidTechException;
import studentsapp.exceptions.InvalidNameException;

import java.util.Scanner;

public class Student {

    private String name;
    private int age;
    private Tech tech;

    public Student(Scanner sc) {
        try {
            System.out.println("*********************");
            System.out.print("Enter your name: ");
            validateName(sc);
            System.out.print("Enter your age: ");
            validateAge(sc);
            System.out.print("Enter tech name: ");
            Tech.printTechs();
            validateTech(sc);
            System.out.println("*********************");
        } catch (InvalidNameException | InvalidAgeException | InvalidTechException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Student creation failed due to invalid input.");
        }
    }

    // Asignación directa
    public Student(String name, int age, Tech tech) {
        this.name = name;
        this.age = age;
        this.tech = tech;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Tech getTech() {
        return tech;
    }

    public void setTech(Tech tech) {
        this.tech = tech;
    }

    public void validateName(Scanner sc) throws InvalidNameException {
        while (true) {
            try {
                System.out.println();
                this.name = sc.nextLine();
                if (this.name.isEmpty()) {
                    throw new InvalidNameException("The name cannot be empty. Please try again.");
                }
                break;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void validateAge(Scanner sc) throws InvalidAgeException {
        while (true) {
            try {
                System.out.println();
                if (!sc.hasNextInt()) {
                    sc.next();
                    throw new InvalidAgeException("Enter a valid age (a positive integer).");
                }
                this.age = sc.nextInt();
                if (this.age < 18 || this.age > 65) {
                    throw new InvalidAgeException("Age must be set between 18 and 65.");
                }
                sc.nextLine();
                break;
            } catch (InvalidAgeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void validateTech(Scanner sc) throws InvalidTechException {
        while (true) {
            try {
                this.tech = Tech.valueOf(sc.nextLine().toUpperCase()); // Convertir a mayúsculas para coincidencia
                break; // Salir si no lanza excepción
            } catch (IllegalArgumentException e) {
                System.out.println("Enter a valid tech.");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("""
                Name: %s
                Age: %d
                Tech: %s
                ********************""", getName(), getAge(), getTech());
    }

    public static Student parse(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid input format for Student: " + line);
        }
        try {
            String name = parts[0].split("=")[1].trim();
            int age = Integer.parseInt(parts[1].split("=")[1].trim());
            Tech tech = Tech.valueOf(parts[2].split("=")[1].trim().toUpperCase());
            return new Student(name, age, tech);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing Student: " + e.getMessage());
        }
    }
}



