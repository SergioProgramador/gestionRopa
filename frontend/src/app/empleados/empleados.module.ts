import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListEmpleadosComponent } from './list-empleados/list-empleados.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { AppRoutingModule } from '../app-routing.module';
import { AddEmpleadoComponent } from './add-empleado/add-empleado.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { EditEmpleadoComponent } from './edit-empleado/edit-empleado.component';

@NgModule({
  declarations: [ListEmpleadosComponent, SidenavComponent, AddEmpleadoComponent, EditEmpleadoComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [
    ListEmpleadosComponent,
    FormsModule,
    HttpClientModule
  ]
})
export class EmpleadosModule { }
