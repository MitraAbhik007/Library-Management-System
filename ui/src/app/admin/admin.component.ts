// import { HttpClient } from '@angular/common/http';
// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// @Component({
//   selector: 'app-admin',
//   templateUrl: './admin.component.html',
//   styleUrls: ['./admin.component.css']
// })
// export class AdminComponent implements OnInit {

//   title = 'Admins Management'
//   admins: any = [];

//   isGreen = true
  

//   constructor(private router: Router, private http: HttpClient) { }

//   ngOnInit(): void {
//     this.fetchAllAdmins()
//   }

//   BooksIssued(){
   
//     console.log("addAdmins button clicked!!")
//     this.router.navigateByUrl('/BooksIssued')
//   }

//   fetchAllAdmins(){
//     this.http.get("http://localhost:8080/admins/getAllAdmins")
//     .subscribe(resp =>{
//       this.admins = resp;
//       console.log('Admins retrieved successfully:', this.admins)
//     }, error => {
//       console.error('Error retrieving admins:', error);
//     });
//   }

//   deleteAdmin(adminId:Number){
    
//     const url = 'http://localhost:8080/admins/deleteAdmins/' +adminId
//     console.log(url)
//     this.http.delete(url)
//     .subscribe(resp => {
//       console.log('Admin deleted successfully');
//       this.fetchAllAdmins()
//     }, error => {
//       console.error('Error deleting admin:', error);
//     });
//   }

// }
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-book',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  title = 'Books Management'
  books: any = [];

  isGreen = true
  

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchAllBooks()
  }

  addBooks(){
   
    console.log("addBooks button clicked!!")
    this.router.navigateByUrl('/add-books')
  }
  BooksIssued(){
   
    console.log("addBooks button clicked!!")
    this.router.navigateByUrl('/BooksIssued')
  }

  fetchAllBooks(){
    this.http.get("http://localhost:8080/books/getAllBooks")
    .subscribe(resp =>{
      this.books = resp;
      console.log('Books retrieved successfully:', this.books)
    }, error => {
      console.error('Error retrieving books:', error);
    });
  }

  deleteBook(bookId:Number){
    
    const url = 'http://localhost:8080/books/deleteBooks/' +bookId
    console.log(url)
    this.http.delete(url)
    .subscribe(resp => {
      console.log('Book deleted successfully');
      this.fetchAllBooks()
    }, error => {
      console.error('Error deleting book:', error);
    });
  }

}