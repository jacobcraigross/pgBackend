package com.crud.crud.student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean // ---> command line runner BEAN
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student alex = new Student(
                    "Alex Smith",
                    "alxsmth@gmail.com",
                    LocalDate.of(1999, Month.JULY, 1)
            );

            Student jillian = new Student(
                    "Jillian Jacobs",
                    "jjjj@gmail.com",
                    LocalDate.of(1998, Month.AUGUST, 22)
            );

            Student josephine = new Student(
                    "Josephine Walters",
                    "jw345@gmail.com",
                    LocalDate.of(1997, Month.JANUARY, 13)
            );

            Student gary = new Student(
                    "Gary Rovelle",
                    "garyrov@gmail.com",
                    LocalDate.of(1996, Month.DECEMBER, 2)
            );

            Student john = new Student(
                    "John Lopez",
                    "jlopez@gmail.com",
                    LocalDate.of(1999, Month.DECEMBER, 15)
            );

            Student will = new Student(
                    "Will Reyes",
                    "willy@gmail.com",
                    LocalDate.of(1998, Month.JUNE, 5)
            );

            Student dorothy = new Student(
                    "Dorothy Emma",
                    "demma@gmail.com",
                    LocalDate.of(1996, Month.NOVEMBER, 21)
            );

            Student nelson = new Student(
                    "Nelson Fridkin",
                    "nellyburger@gmail.com",
                    LocalDate.of(1998, Month.JULY, 30)
            );

            repository.saveAll(List.of(dorothy, nelson, gary, john, will, josephine, jillian, alex));
        };
    }
}
