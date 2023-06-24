package com.xfactor.openlibrary.controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xfactor.openlibrary.domain.BooksIssued;
//import com.xfactor.openlibrary.domain.Student;

@RestController
@RequestMapping("BooksIssued")
public class BooksIssuedController {

    @GetMapping("/getAllBooksIssued")
public List<BooksIssued> getAllBooksIssued() {
    List<BooksIssued> booksIssuedList = new ArrayList<>();

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_LibraryManagement",
                "root",
                "abhik"
        );

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books_issued");

        while (resultSet.next()) {
            String isbn = resultSet.getString("isbn");
            Long s_id = resultSet.getLong("s_id");
            Date taken_date = resultSet.getDate("taken_date");
            Date return_date = resultSet.getDate("return_date");

            BooksIssued booksIssued = new BooksIssued(isbn, s_id, taken_date, return_date);
            booksIssuedList.add(booksIssued);
        }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }

    return booksIssuedList;
}

}
