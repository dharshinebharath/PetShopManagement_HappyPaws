// import { Component } from '@angular/core';
// import { FormsModule } from '@angular/forms';

// @Component({
//   selector: 'app-employee-form',
//   standalone: true,
//   imports: [FormsModule],
//   templateUrl: './employee-form.html',
//   styleUrls: ['./employee-form.css']
// })
// export class EmployeeFormComponent {

//   employee = {
//     firstName: '',
//     lastName: '',
//     position: '',
//     phone: '',
//     email: '',
//     address: ''
//   };

//   saveEmployee() {
//     console.log(this.employee);
//   }

// }
import { Component, Output, EventEmitter } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './employee-form.html',
  styleUrls: ['./employee-form.css']
})
export class EmployeeFormComponent {

  @Output() cancelClicked = new EventEmitter<void>();

  employee = {
    firstName: '',
    lastName: '',
    position: '',
    phone: '',
    email: '',
    address: ''
  };

  saveEmployee() {
    console.log(this.employee);
  }

  cancel() {
    this.cancelClicked.emit();
  }

}