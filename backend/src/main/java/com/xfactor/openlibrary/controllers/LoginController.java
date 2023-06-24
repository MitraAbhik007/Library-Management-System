package com.xfactor.openlibrary.controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    @GetMapping("/login1/{username}/{password}")
    public ResponseEntity<Map<String, String>> loginCont(@PathVariable("username") String username, @PathVariable("password") String password) {
        String userType = "Null";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_LibraryManagement", "root","abhik"
            );
            PreparedStatement statement = connection.prepareStatement("SELECT user FROM details WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userType = resultSet.getString("user");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("userType", userType);
        
        return ResponseEntity.ok(response);
    }
}


