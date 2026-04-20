import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-supplier-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './supplier-list.html'
})
export class SupplierListComponent {

  @Input() searchText = '';
  @Input() showForm = false;
  @Output() closeForm = new EventEmitter();

  suppliers = [
    { id: 1, name: 'ABC Suppliers', contact: 'Ravi', phone: '9876543210', email: 'abc@mail.com' }
  ];

  newSupplier: any = {};

  get filteredSuppliers() {
    if (!this.searchText) return this.suppliers;

    return this.suppliers.filter(s =>
      s.name.toLowerCase().includes(this.searchText.toLowerCase())
    );
  }

  addSupplier() {
    this.newSupplier.id = this.suppliers.length + 1;
    this.suppliers.push(this.newSupplier);
    this.newSupplier = {};
    this.closeForm.emit();
  }
}