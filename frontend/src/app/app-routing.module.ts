import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListCategoriasComponent } from './categorias/list-categorias/list-categorias.component';
import { HomeComponent } from './base/home/home.component';
import { ListProveedoresComponent } from './proveedores/list-proveedores/list-proveedores.component';
import { ListClientesComponent } from './clientes/list-clientes/list-clientes.component';
import { VerClienteComponent } from './clientes/ver-cliente/ver-cliente.component';
import { VerFacturaComponent } from './facturas/ver-factura/ver-factura.component';
import { AddFacturaComponent } from './facturas/add-factura/add-factura.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'categorias/showcategorias', component: ListCategoriasComponent},  
  {path: 'proveedores/showproveedores', component: ListProveedoresComponent},
  {path: 'clientes/showclientes', component: ListClientesComponent},
  {path: 'clientes/ver/:id', component: VerClienteComponent},
  {path: 'facturas/ver/:id', component: VerFacturaComponent},
  {path: 'facturas/create/:clienteId', component: AddFacturaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
