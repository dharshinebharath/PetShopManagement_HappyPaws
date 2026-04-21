import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';


import { SupplierForm } from '../supplier-form/supplier-form';
import { SupplierList } from '../supplier-list/supplier-list';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-supplier-dashboard',
  standalone: true,
  imports: [CommonModule, SupplierList, SupplierForm,FormsModule],
  templateUrl: './supplier-dashboard.html',
  styleUrl: './supplier-dashboard.css',
})
export class SupplierDashboard {
  baseUrl = 'http://localhost:8082/api/v1';

  // ✅ VIEW CONTROL
  view: string = 'dashboard'; // dashboard | list | form
  formType: string = '';

  // ✅ DATA
  supplierList: any[] = [];
  selectedSupplier: any = {};

  // ✅ SEARCH INPUT
  supplierId: number | null = null;

  constructor(private http: HttpClient) {}

  // ---------------- GET ALL ----------------
  getAll() {
    this.view = 'list';

    this.http.get<any[]>(`${this.baseUrl}/supplier`)
      .subscribe(res => {
        this.supplierList = res;
      });
  }

  // ---------------- GET BY ID ----------------
  getById() {
    if (!this.supplierId) return;

    this.view = 'list';

    this.http.get(`${this.baseUrl}/supplier/${this.supplierId}`)
      .subscribe(res => {
        this.supplierList = [res];
      });
  }

  // ---------------- ADD ----------------
  add() {
    this.view = 'form';
    this.formType = 'add';
    this.selectedSupplier = {};
  }

  // ---------------- UPDATE ----------------
  update() {
    this.view = 'form';
    this.formType = 'update';
  }

  // ---------------- DELETE BY ID ----------------
  deleteById() {
    if (!this.supplierId) return;

    this.http.delete(`${this.baseUrl}/supplier/${this.supplierId}`)
      .subscribe(() => {
        this.getAll();
      });
  }

  // ---------------- EDIT ----------------
  editSupplier(supplier: any) {
    this.view = 'form';
    this.formType = 'update';
    this.selectedSupplier = { ...supplier };
  }

  // ---------------- SAVE (POST) ----------------
  saveSupplier(data: any) {
    this.http.post(`${this.baseUrl}/supplier`, data)
      .subscribe(() => {
        this.getAll();
      });
  }

  // ---------------- UPDATE (PUT) ----------------
  updateSupplier(data: any) {
    this.http.put(`${this.baseUrl}/supplier/${data.supplier_id}`, data)
      .subscribe(() => {
        this.getAll();
      });
  }

  // ---------------- BACK ----------------
  back() {
    this.view = 'dashboard';
  }

}