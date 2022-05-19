package com.crud.crud.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {
    // INSTANTIATING THE CLASS TO USE BELOW --------------------------------------------------------------------------
    @Autowired
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    // GET SERVICE THAT RETURNS A LIST OF ALL STUDENTS. JPA OTB MAGIC! -----------------------------------------------
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    // POST SERVICE WITH EMAIL CHECK ---------------------------------------------------------------------------------
    public void addNewStudent(Student student) {
        // this logic is defined in the StudentRepository with the @Query annotation
        Optional<Student> studentOptional = studentRepository.findByStudentEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("ERROR: EMAIL TAKEN.");
        } else {
            studentRepository.save(student);
        }
    }
    // DELETE SERVICE BY STUDENT ID PATH VARIABLE --------------------------------------------------------------------
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("ERROR: ID DOES NOT EXIST.");
        } else {
            studentRepository.deleteById(studentId);
        }
    }
    // UPDATE (or edit / put) SERVICE FOR CHANGING NAME AND EMAIL VIA STUDENT ID -------------------------------------
    // note: below for the email PUT, we will also run an additional check to see if the email is already taken.
    @Transactional // ---> for combining multiple writes to the DB as a single atomic operation
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("ERROR: ID DOES NOT EXIST."));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findByStudentEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("ERROR: EMAIL ALREADY TAKEN.");
            } else {
                student.setEmail(email);
            }
        }
    }
}


