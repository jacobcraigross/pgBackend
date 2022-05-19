package com.crud.crud.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // ---> responsible for data access
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Business logic to add custom retrieval methods or filters on querying the DB. Very relevant to work stuff.
    // SELECT * FROM student WHERE email = "email@here.com"
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findByStudentEmail(String email);

}
