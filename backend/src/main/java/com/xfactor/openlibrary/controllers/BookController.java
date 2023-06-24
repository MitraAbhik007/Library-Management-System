package com.xfactor.openlibrary.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.Statement;
import com.xfactor.openlibrary.domain.Book;
import com.xfactor.openlibrary.repositories.BookRepository;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("books")
public class BookController{
    private BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/saveBooks")
    public Book saveBook(@RequestBody Book book){
         try {
        // Establish database connectivity
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_LibraryManagement",
                "root",
                "abhik"
        );
        
        // Prepare the SQL statement to insert a new book
        String insertQuery = "INSERT INTO books (id,name, author, isbn, publicationDate, publisher, copies, category, genre, subgenre) " +
                "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        
        // Set the parameter values using the Book object
        statement.setLong(1, book.getId());
        statement.setString(2, book.getName());
        statement.setString(3, book.getAuthor());
        statement.setString(4, book.getIsbn());
        statement.setString(5, book.getPublicationDate());
        statement.setString(6, book.getPublisher());
        statement.setLong(7, book.getCopies());
        statement.setString(8, book.getCategory());
        statement.setString(9, book.getGenre());
        statement.setString(10, book.getSubgenre());
        
        // Execute the SQL statement
        statement.executeUpdate();
        
        // Close the statement and connection
        statement.close();
        connection.close();
        
        // Return the saved Book object
        return book;
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        return null; // Or handle the exception in an appropriate way
    }    
        }
    

    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book book) {
        if (book.getId() != null) {
            Book book2 = bookRepository.save(book);
            return book2;
        }
        return null;
    }

    // @GetMapping("/getAllBooks")
    // public List<Book> getAllBooks(){
        
    // }
    @GetMapping("/getAllBooks")
     //public List<books> displayallbooks(){
        public List<Book> getAllBooks(){
            List<Book> ll= new ArrayList<Book>();
          
            try {
                // below two lines are used for connectivity.
                Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_LibraryManagement",
                "root",
                "abhik"
        );
     
                
        java.sql.Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String author = resultSet.getString("author");
                    String isbn = resultSet.getString("isbn");
                    String publicationDate = resultSet.getString("publicationDate");
                    String publisher = resultSet.getString("publisher");
                    Long copies = resultSet.getLong("copies");
                    String category = resultSet.getString("category");
                    String genre = resultSet.getString("genre");
                    String subgenre = resultSet.getString("subgenre");
                   
                    Book b = new Book(id, name, author, isbn, publicationDate, publisher, copies, category, genre, subgenre);
                    ll.add(b);
                }
                resultSet.close();
                statement.close();
                connection.close();
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return ll;
        }

    // @GetMapping("/findTotalBooks")
    // public Long findTotalBooks(){
    //     return bookRepository.count();
    // }
    
    // @GetMapping("findByBookId/{id}")
    // public Book findById(@PathVariable Long id) {
    //     Optional<Book> optionalOfBook = bookRepository.findById(id);
    //     if (optionalOfBook.isPresent()) {
    //         return optionalOfBook.get();
    //     }
    //     return null;
    // }

    // @DeleteMapping("deleteBooks/{id}")
    // public void deleteBooks(@PathVariable Long id){
    //     bookRepository.deleteById(id);
    // }

    // @GetMapping("findByisbn/{isbn}")
    // public List<Book> findByisbn(@PathVariable String isbn) {
    //     List<Book> books = bookRepository.findByIsbn(isbn);
    //     return books;
    // }
}

