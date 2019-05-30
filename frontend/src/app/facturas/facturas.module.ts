import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VerFacturaComponent } from './ver-factura/ver-factura.component';
import { FormsModule, ReactiveFormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { AddFacturaComponent } from './add-factura/add-factura.component';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';

@NgModule({
  declarations: [VerFacturaComponent, AddFacturaComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    
  ],
  exports:[
    VerFacturaComponent,
    FormsModule,
    HttpClientModule,
    AddFacturaComponent,
    MatAutocompleteModule,
    MatInputModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    
  ]
})
export class FacturasModule { }
