// // import { Component, signal } from '@angular/core';
// // import { RouterOutlet } from '@angular/router';
// // import { Sidebar } from "./shared/components/sidebar/sidebar";
// // import { Dashboard } from "./features/dashboard/dashboard";
// // import { EmployeeFormComponent } from './features/employees/employee-form/employee-form';
// // import { EmployeeListComponent } from './features/employees/employee-list/employee-list';

// // @Component({
// //   selector: 'app-root',
// //   standalone: true,  
// //   imports: [RouterOutlet,  EmployeeFormComponent,EmployeeListComponent],
// //   templateUrl: './app.html',
// //   styleUrls: ['./app.css'] 
// // })
// // export class App {
// //   protected readonly title = signal('frontend');
// // }
// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { EmployeeFormComponent } from './features/employees/employee-form/employee-form';
// import { EmployeeListComponent } from './features/employees/employee-list/employee-list';
// import { InventoryComponent } from './features/inventory/inventory';

// @Component({
//   selector: 'app-root',
//   standalone: true,
//   imports: [CommonModule,InventoryComponent],
//   template: `
//     <app-employee-list 
//       *ngIf="!showForm" 
//       (addClicked)="openForm()">
//     </app-employee-list>

//     <app-employee-form 
//       *ngIf="showForm"
//       (cancelClicked)="closeForm()">
//     </app-employee-form>
//   `
// })
// export class App {

//   showForm = false;

//   openForm() {
//     this.showForm = true;
//   }

//   closeForm() {
//     this.showForm = false;
//   }

// }
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.html'
})
export class AppComponent {}