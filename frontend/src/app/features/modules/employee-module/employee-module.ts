// This file holds the Angular logic for employee module.
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-employee-module',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './employee-module.html',
  styleUrls: ['./employee-module.css']
})
export class EmployeeModule {}
