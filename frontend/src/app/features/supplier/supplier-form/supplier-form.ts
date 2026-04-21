import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-supplier-form',
  imports: [FormsModule],
  templateUrl: './supplier-form.html',
  styleUrl: './supplier-form.css',
})
export class SupplierForm {
 @Input() type: string = ''; // add | update
  @Input() supplier: any = {};

  @Output() saveEvent = new EventEmitter<any>();
  @Output() updateEvent = new EventEmitter<any>();

  save() {
    this.saveEvent.emit(this.supplier);
  }

  update() {
    this.updateEvent.emit(this.supplier);
  }
}
