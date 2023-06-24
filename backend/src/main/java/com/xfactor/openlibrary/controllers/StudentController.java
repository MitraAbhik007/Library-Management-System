package com.xfactor.openlibrary.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.ResultSet;

import com.mysql.cj.xdevapi.Statement;
import com.xfactor.openlibrary.domain.Student;
import com.xfactor.openlibrary.domain.TakeBook;
import com.xfactor.openlibrary.domain.ReturnBook;
import com.xfactor.openlibrary.repositories.StudentRepository;

@RestController
@RequestMapping("students")
public class StudentController{
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private StudentRepository studentRepository;

    
    @PostMapping("/saveStudents")
    public Student saveStudent(@RequestBody Student Student){
        String url = "jdbc:mysql://localhost:3306/db_LibraryManagement";
        String username = "root";
        String password = "abhik";
        
             
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection connection = DriverManager.getConnection(url, username, password);
           
 
             String sql = "INSERT INTO students (id,name,dept,rollNumber,BirthDate,mobileNo) VALUES (?, ?, ?, ?, ?, ?)";
             PreparedStatement statement = connection.prepareStatement(sql);
   
                 statement.setLong(1,Student.getId());
                 statement.setString(2,Student.getName());
                 statement.setString(3, Student.getDept());
                 statement.setString(4,Student.getRollNumber());
                 statement.setString(5,Student.getBirthDate());
                 statement.setString(6,Student.getMobileNo());
                 statement.addBatch();

                 int[] updateCounts = statement.executeBatch();
                 for (int updateCount : updateCounts) {
                     if (updateCount == PreparedStatement.EXECUTE_FAILED) {
                         System.out.println("Failed to insert row.");
                     } else {
                         System.out.println("Row inserted successfully!"); 
                     }
                 }
             statement.close();
             connection.close();
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         
             return Student;
    }
    @PostMapping("/TakeBook")
    public TakeBook takebook(@RequestBody TakeBook takebook)
    {
        String url = "jdbc:mysql://localhost:3306/db_LibraryManagement";
        String username = "root";
        String password = "abhik";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
    
            String sql = "INSERT INTO books_issued (isbn,s_id,taken_date,return_date) VALUES (?, ?, ?, ?)";            
            PreparedStatement statement = connection.prepareStatement(sql);
    
            statement.setString(1, takebook.getIsbn());
            statement.setLong(2,takebook.getS_id());
            LocalDate today = LocalDate.now();
            LocalDate futureDate = today.plusDays(15);
            Date sqlDate = Date.valueOf(today);
            Date FutDate = Date.valueOf(futureDate);
            statement.setDate(3,sqlDate);
            statement.setDate(4,FutDate);
        
            statement.addBatch();
            int[] updateCounts = statement.executeBatch();
            for (int updateCount : updateCounts) {
                if (updateCount == PreparedStatement.EXECUTE_FAILED) {
                    System.out.println("Failed to insert row.");
                } else {
                    System.out.println("Row inserted successfully!"); 
                }
            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return takebook;
        
    }
    @PostMapping("/ReturnBook")
    public ReturnBook returnbook(@RequestBody ReturnBook returnbook)
    {
        String url = "jdbc:mysql://localhost:3306/db_LibraryManagement";
        String username = "root";
        String password = "abhik";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
    
            String sql = "DELETE FROM books_issued WHERE isbn=? and s_id=?";            
            PreparedStatement statement = connection.prepareStatement(sql);
    
            statement.setString(1, returnbook.getIsbn());
            statement.setLong(2,returnbook.getS_id());    
            statement.addBatch();
            int[] updateCounts = statement.executeBatch();
            for (int updateCount : updateCounts) {
                if (updateCount == PreparedStatement.EXECUTE_FAILED) {
                    System.out.println("Failed to insert row.");
                } else {
                    System.out.println("Row inserted successfully!"); 
                }
            }
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return returnbook;
        
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        if (student.getId() != null) {
            Student student2 = studentRepository.save(student);
            return student2;
        }
        return null;
    }
    @GetMapping("/getAllStudents")
public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_LibraryManagement",
                "root",
                "abhik"
        );

        java.sql.Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String dept = resultSet.getString("dept");
            String rollNumber = resultSet.getString("rollNumber");
            String birthDate = resultSet.getString("BirthDate");
            String mobileNo = resultSet.getString("mobileNo");

            Student student = new Student(id, name, dept, rollNumber, birthDate, mobileNo);
            students.add(student);
        }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }

    return students;
}


    @GetMapping("/findTotalStudents")
    public Long findTotalStudents(){
        return studentRepository.count();
    }
    
    @GetMapping("findByStudentId/{id}")
    public Student findById(@PathVariable Long id) {
        Optional<Student> optionalOfStudent = studentRepository.findById(id);
        if (optionalOfStudent.isPresent()) {
            return optionalOfStudent.get();
        }
        return null;
    }

    @DeleteMapping("deleteStudents/{id}")
    public void deleteStudents(@PathVariable Long id){
        String url = "jdbc:mysql://localhost:3306/db_LibraryManagement";
        String username = "root";
        String password = "abhik";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
    
            String sql = "DELETE FROM students where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
    
            statement.setLong(1, id);
            statement.addBatch();
            statement.executeBatch();
    
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("findByname/{name}")
    public List<Student> findByname(@PathVariable String name) {
        List<Student> students = studentRepository.findByName(name);
        return students;
    }
}