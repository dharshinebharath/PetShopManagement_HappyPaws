import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-supplier-list',
  imports: [CommonModule],
  templateUrl: './supplier-list.html',
  styleUrl: './supplier-list.css',
})
export class SupplierList {
  @Input() supplierList: any[] = [];

  @Output() deleteEvent = new EventEmitter<number>();
  @Output() editEvent = new EventEmitter<any>();

  deleteSupplier(id: number) {
    this.deleteEvent.emit(id);
  }

  editSupplier(supplier: any) {
    this.editEvent.emit(supplier);
  }
}
