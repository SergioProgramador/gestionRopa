import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListClientesComponent } from './list-clientes/list-clientes.component';
import { AddClienteComponent } from './add-cliente/add-cliente.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { EditClienteComponent } from './edit-cliente/edit-cliente.component';
import { VerClienteComponent } from './ver-cliente/ver-cliente.component';
import { AppRoutingModule } from '../app-routing.module';
import { SidenavComponent } from './sidenav/sidenav.component';

@NgModule({
  declarations: [ListClientesComponent, AddClienteComponent, EditClienteComponent, VerClienteComponent, SidenavComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  exports: [
    ListClientesComponent, 
    AddClienteComponent,
    VerClienteComponent,
    FormsModule,
    HttpClientModule
  ],
})
export class ClientesModule { }
