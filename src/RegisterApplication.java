import studentsapp.service.ServiceStudent;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegisterApplication {
    public static void main(String[] args) {
        ServiceStudent serviceStudent = new ServiceStudent();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Student Registration System ===");
            System.out.println("1. Register a new student");
            System.out.println("2. List all students");
            System.out.println("3. Edit a student");
            System.out.println("4. Delete a student");
            System.out.println("5. Load students from file");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consumir carácter de nueva línea
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                sc.nextLine(); // Limpiar el buffer de entrada
                continue;
            }

            switch (choice) {
                case 1:
                    serviceStudent.registerStudent();
                    break;
                case 2:
                    serviceStudent.listAllStudents();
                    break;
                case 3:
                    serviceStudent.editStudent();
                    break;
                case 4:
                    serviceStudent.deleteStudent();
                    break;
                case 5:
                    serviceStudent.loadStudent();
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
