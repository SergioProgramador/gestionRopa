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
import { SidenavComponent } from './sidenav/sidenav.component';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [ListProveedoresComponent, AddProveedorComponent, EditProveedorComponent, SidenavComponent],
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
    AppRoutingModule
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
    SidenavComponent
  ],
  entryComponents: [AddProveedorComponent, EditProveedorComponent],
})
export class ProveedoresModule { }
