import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-BooksIssued',
  templateUrl: './BooksIssued.component.html',
  styleUrls: ['./BooksIssued.component.css']
})
export class BooksIssuedComponent implements OnInit {

  title = 'Books Issued';
  BooksIssued: any = [];

  isGreen = true;
  

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.fetchAllBooksIssued();
  }

  // addStudents(): void {
  //   console.log("addStudents button clicked!!");
  //   this.router.navigateByUrl('/add-students');
  // }

  fetchAllBooksIssued(): void {
    this.http.get("http://localhost:8080/BooksIssued/getAllBooksIssued")
      .subscribe(
        (resp: any) => {
          this.BooksIssued = resp;
          console.log('Students retrieved successfully:', this.BooksIssued);
        },
        (error: any) => {
          console.error('Error retrieving students:', error);
        }
      );
  }
}
  // deleteStudent(studentId:Number){
    
  //   const url = 'http://localhost:8080/students/deleteStudents/' +studentId
  //   console.log(url)
  //   this.http.delete(url)
  //   .subscribe(resp => {
  //     console.log('Student deleted successfully');
  //     this.fetchAllStudents()
  //   }, error => {
  //     console.error('Error deleting student:', error);
  //   });
  // }

