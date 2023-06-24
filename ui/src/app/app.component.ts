// // import { Component } from '@angular/core';


// // @Component({
// //   selector: 'app-root',
// //   templateUrl: './app.component.html',
// //   styleUrls: ['./app.component.css']
// // })
// // export class AppComponent {
// //   title = 'openlibrary';
// // }

// import { Component } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Router, Routes } from '@angular/router';
// import { StudentComponent } from './student/student.component';
// import { AdminComponent } from './admin/admin.component';


// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.css']
// })

// export class AppComponent {
//   username: string | undefined;
//   password: string | undefined;
//   title = 'openlibrary';
//   isLoggedIn: boolean | undefined;

//   constructor(private http: HttpClient, private router: Router) {}

//   login() {
//     const url = `http://localhost:8080/login/login1/${this.username}/${this.password}`;
//     this.http.get<any>(url).subscribe(
//       response => {
//         const userType = response.userType;
//         if (userType === 'Student') {
//           this.isLoggedIn = true; // Update isLoggedIn to true
//           this.router.navigate(['/students']);
//         } else if (userType === 'Admin') {
//           this.isLoggedIn = true; // Update isLoggedIn to true
//           this.router.navigate(['/admins']);
//         } else {
//           this.router.navigate(['/error']);
//         }
//       },
//       error => {
//         console.log('An error occurred', error);
//       }
//     );
//   }
//   logout() {
//     // Implement logout functionality here
//     this.isLoggedIn = false;
//     this.router.navigate(['/login']);
//   }
// }

import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  username: string | undefined;
  password: string | undefined;
  title = 'openlibrary';
  isLoggedIn: boolean | undefined;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit() {
    console.log('Dhur');
    this.checkSessionStatus();
  }

  login() {
    console.log('hello');
    const url = `http://localhost:8080/login/login1/${this.username}/${this.password}`;
    this.http.get<any>(url).subscribe(
      response => {
        const userType = response.userType;
        if (userType === 'Student') {
          this.isLoggedIn = true; // Update isLoggedIn to true
          this.router.navigate(['/students']);
        } else if (userType === 'Admin') {
          this.isLoggedIn = true; // Update isLoggedIn to true
          this.router.navigate(['/admins']);
        } else {
          this.router.navigate(['/error']);
        }
      },
      error => {
        console.log('An error occurred', error);
      }
    );
  }

  logout() {
    // Implement logout functionality here
    this.isLoggedIn = false;
    this.router.navigate(['/login']);
  }

  // checkSessionStatus() {
  //   // Check if the user is logged in
  //   // You can modify this logic based on your session implementation
  //   const sessionData = sessionStorage.getItem('sessionData');
  //   console.log('hi');
  //   console.log(sessionData);
  //   this.isLoggedIn = sessionData ? true : false;

  //   // Redirect to the appropriate page based on the session status
  //   if (this.isLoggedIn) {
  //     console.log('true')
  //     this.router.navigate(['/students','/books']); // or ['/admins'] depending on the user type
  //   } else {
  //     //console.log('false')
  //     this.router.navigate(['/login']);
  //   }
  // }
  checkSessionStatus() {
    const sessionData = sessionStorage.getItem('sessionData');
    if (!sessionData) {
      // No session data found, user is not logged in
      this.isLoggedIn = false;
      this.router.navigate(['/login']);
      return;
    }
  
    this.isLoggedIn = true;
    const currentRoute = this.router.url;
    if (currentRoute === '/books') {
      // If the current route is '/books' or '/booksissued', stay on the current route
      return;
    }
  
    // Redirect to the appropriate route based on the user type
    const parsedSessionData = JSON.parse(sessionData);
    const userType = parsedSessionData.userType;
    if (userType === 'Student') {
      this.router.navigate(['/students']);
     
    } else if (userType === 'Admin') {
      this.router.navigate(['/admins']);
    } else {
      this.router.navigate(['/error']);
    }
  }
  
  
 
  
  
}
