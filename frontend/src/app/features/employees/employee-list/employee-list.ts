// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';

// @Component({
//   selector: 'app-employee-list',
//   standalone: true,
//   imports: [CommonModule,FormsModule],
//   templateUrl: './employee-list.html',
//   styleUrls: ['./employee-list.css']
// })
// export class EmployeeListComponent {

//   searchText: string = '';

//   employees = [
//     {
//       id: 1,
//       firstName: 'John',
//       lastName: 'Doe',
//       position: 'Manager',
//       phone: '9876543210',
//       email: 'john@example.com',
//       address: 'Chennai'
//     },
//     {
//       id: 2,
//       firstName: 'Priya',
//       lastName: 'Sharma',
//       position: 'Staff',
//       phone: '9123456780',
//       email: 'priya@example.com',
//       address: 'Bangalore'
//     }
//   ];

// }
import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employee-list.html',
  styleUrls: ['./employee-list.css']
})
export class EmployeeListComponent {

  @Output() addClicked = new EventEmitter<void>();

  searchText: string = '';

  employees = [
    {
      id: 1,
      firstName: 'John',
      lastName: 'Doe',
      position: 'Manager',
      phone: '9876543210',
      email: 'john@example.com',
      address: 'Chennai'
    },
    {
      id: 2,
      firstName: 'Priya',
      lastName: 'Sharma',
      position: 'Staff',
      phone: '9123456780',
      email: 'priya@example.com',
      address: 'Bangalore'
    }
  ];

  openForm() {
    this.addClicked.emit();
  }
  get filteredEmployees() {
  if (!this.searchText) return this.employees;

  const search = this.searchText.toLowerCase();

  return this.employees.filter(emp =>
    emp.firstName.toLowerCase().includes(search) ||
    emp.lastName.toLowerCase().includes(search) ||
    emp.email.toLowerCase().includes(search) ||
    emp.phone.toLowerCase().includes(search) ||
    emp.position.toLowerCase().includes(search)
  );
}

}