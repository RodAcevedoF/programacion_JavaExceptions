# Student Registration System

This is a simple Java console application developed for academic purposes. It provides a basic system to register, list, edit, delete, and load students, storing their details in a text file.

## Features

- **Register a new student**: Input name, age, and technology.
- **List all students**: Display all registered students.
- **Edit a student**: Update details for an existing student.
- **Delete a student**: Remove a student by name.
- **Load students from file**: Read existing students from `data/students_file.txt` at startup.

## Project Structure

- `.idea/`: IDE configuration files.
- `data/students_file.txt`: Storage file for student records.
- `src/`
  - `exceptions/`: Custom exceptions for invalid inputs.
    - `InvalidAgeException.java`
    - `InvalidNameException.java`
    - `InvalidTechException.java`
  - `model/`: Data models.
    - `Student.java`
    - `Tech.java`
  - `repository/`: File-based CRUD operations.
    - `StudentRepository.java`
  - `service/`: Business logic and user interaction.
    - `IServiceStudent.java`
    - `ServiceStudent.java`
  - `RegisterApplication.java`: Main entry point.

## How to Run

1. **Clone or download** the project.
2. **Compile** all Java sources:
   ```bash
   javac -d out src/**/*.java
   ```
3. **Run** the application:
   ```bash
   java -cp out RegisterApplication
   ```

## Example Usage

```
=== Student Registration System ===
1. Register a new student
2. List all students
3. Edit a student
4. Delete a student
5. Load students from file
6. Exit
Choose an option: 1
Enter name: Alice
Enter age: 21
Select technology (e.g., JAVA, PYTHON, JAVASCRIPT): JAVA
Student registered successfully!

Choose an option: 2
List of students:
- Alice, age 21, tech: JAVA
```

## Requirements

- **Java 11** or higher

---
