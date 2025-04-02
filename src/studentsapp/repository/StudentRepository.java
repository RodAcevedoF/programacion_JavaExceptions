package studentsapp.repository;

import studentsapp.model.Student;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StudentRepository {
    private final List<Student> studentList = new LinkedList<>();
    private final File file = new File("./data/students_file.txt");

    public StudentRepository() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                loadFromFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating or loading the file: " + e.getMessage());
        }
    }

    public boolean addStudent(Student student) {
        boolean exists = studentList.stream()
                .anyMatch(s -> s.getName().equalsIgnoreCase(student.getName()));
        if (exists) {
            return false; // Indica que no se pudo agregar
        }
        studentList.add(student);
        // Guardar solo nuevo estudiante en el archivo [true como segundo parametro para !sobreescribir]
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.format("name=%s,age=%d,tech=%s",
                    student.getName(),
                    student.getAge(),
                    student.getTech().name()));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving student to file: " + e.getMessage());
        }
        return true; // Indica que estudiante fue agregado correctamente
    }

    public void removeStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the student to delete: ");
        String nameToDelete = sc.nextLine();
        // Se elimina estudiante de la lista
        boolean removed = studentList.removeIf(student -> student.getName().equalsIgnoreCase(nameToDelete));
        if (removed) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
        // Actualizamos el archivo para tener lista actualizada
        saveToFile();
    }

    public List<Student> getAllStudents() {
        // Se devuelve una copia de la lista por modificaciones directas
        return new LinkedList<>(studentList);
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) { // Modo sobrescritura
            for (Student student : studentList) {
                writer.write(String.format("name=%s,age=%d,tech=%s",
                        student.getName(),
                        student.getAge(),
                        student.getTech().name()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        studentList.clear(); // Limpiar la lista antes de cargar datos
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Student student = Student.parse(line); // Parsear línea x linea
                    studentList.add(student);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error parsing a student record: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
