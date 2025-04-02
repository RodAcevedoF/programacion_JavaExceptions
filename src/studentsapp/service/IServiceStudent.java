package studentsapp.service;

import studentsapp.model.Student;

import java.util.List;

public interface IServiceStudent {
    public List<Student> listAllStudents();

    public void registerStudent();

    public void deleteStudent();

    public void loadStudent();

    public void editStudent();
}
