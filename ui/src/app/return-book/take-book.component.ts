
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-take-book',
  templateUrl: './take-book.component.html',
  styleUrls: ['./take-book.component.css']
})
export class ReturnBookComponent implements OnInit {
  TakeBookForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.TakeBookForm = this.formBuilder.group({
      isbn: [''], // Replace with the appropriate initial value
      s_id: [''] // Replace with the appropriate initial value
    });
  }

  TakeBook() {
    let bsData = this.TakeBookForm.value;
    // Handle date to string
    this.http.post('http://localhost:8080/students/ReturnBook',bsData)
    .subscribe(response => {
      console.log('Student saved to DB', response)
      this.router.navigateByUrl('/BooksIssued')
    }, error =>{
      console.error("Error in student save", error)
    }
    );
  }
}
