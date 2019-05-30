import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProveedoresComponent } from './list-proveedores/list-proveedores.component';
import { MatTableModule, MatPaginatorModule, MatSortModule, MatIconModule } from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { AddProveedorComponent } from './add-proveedor/add-proveedor.component';
import { EditProveedorComponent } from './edit-proveedor/edit-proveedor.component';

@NgModule({
  declarations: [ListProveedoresComponent, AddProveedorComponent, EditProveedorComponent],
  imports: [
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    HttpClientModule,
  ],
  exports: [
    ListProveedoresComponent,
    CommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    HttpClientModule,
    AddProveedorComponent,
  ],
  entryComponents: [AddProveedorComponent, EditProveedorComponent],
})
export class ProveedoresModule { }
