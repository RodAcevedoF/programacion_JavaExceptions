package studentsapp.service;

import studentsapp.exceptions.InvalidAgeException;
import studentsapp.exceptions.InvalidTechException;
import studentsapp.exceptions.InvalidNameException;
import studentsapp.model.Tech;
import studentsapp.model.Student;
import studentsapp.repository.StudentRepository;

import java.util.List;
import java.util.Scanner;

public class ServiceStudent implements IServiceStudent {

    private final StudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> listAllStudents() {
        // Cargar desde el archivo para actualizar la lista en memoria
        studentRepository.loadFromFile();
        List<Student> list = studentRepository.getAllStudents();
        if (list.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.print("""   
                    ********************
                    ** Student`s info **
                    ********************
                    """);
            list.forEach(System.out::println);
        }
        return list;
    }

    @Override
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);
        Student newStudent = new Student(sc);
        // Intentamos agregar el estudiante
        boolean added = studentRepository.addStudent(newStudent);
        if (added) {
            System.out.println("Student registered and saved successfully!");
        } else {
            System.out.println("Error: A student with this name already exists. Registration failed.");
        }
    }

    @Override
    public void deleteStudent() {
        studentRepository.removeStudent();
    }

    @Override
    public void loadStudent() {
        studentRepository.loadFromFile();
        System.out.println("Students loaded from file.");
    }

    @Override
    public void editStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the student to edit: ");
        String nameToEdit = sc.nextLine();

        List<Student> students = studentRepository.getAllStudents();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(nameToEdit)) {
                System.out.println("Editing student: " + student);
                // Validación y actualización del nombre
                try {
                    System.out.print("Enter new name: ");
                    student.validateName(sc);
                } catch (InvalidNameException e) {
                    System.out.println("Invalid name. Please enter a valid name.");
                    return;
                }
                // Validación y actualización de la edad
                try {
                    System.out.print("Enter new age: ");
                    student.validateAge(sc);
                } catch (InvalidAgeException e) {
                    System.out.println("Invalid age. Please enter a valid age.");
                    return;
                }
                // Validación y actualización del curso
                try {
                    System.out.print("Enter new Tech: ");
                    String techInput = sc.nextLine().toUpperCase();
                    try {
                        student.setTech(Tech.valueOf(techInput));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidTechException("Invalid tech. Please enter a valid option.");
                    }
                } catch (InvalidTechException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                System.out.println("Student updated successfully!");
                // Se guarda la lista para persistir los cambios
                studentRepository.saveToFile();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
