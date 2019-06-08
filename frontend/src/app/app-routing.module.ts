import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListCategoriasComponent } from './categorias/list-categorias/list-categorias.component';
import { HomeComponent } from './base/home/home.component';
import { ListProveedoresComponent } from './proveedores/list-proveedores/list-proveedores.component';
import { ListClientesComponent } from './clientes/list-clientes/list-clientes.component';
import { VerClienteComponent } from './clientes/ver-cliente/ver-cliente.component';
import { VerFacturaComponent } from './facturas/ver-factura/ver-factura.component';
import { AddFacturaComponent } from './facturas/add-factura/add-factura.component';
import { ListProductosComponent } from './productos/list-productos/list-productos.component';
import { VerProductoComponent } from './productos/ver-producto/ver-producto.component';
import { AddProductoComponent } from './productos/add-producto/add-producto.component';
import { LoginComponent } from './autentificacion/login/login.component';

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'categorias/showcategorias', component: ListCategoriasComponent},  
  {path: 'proveedores/showproveedores', component: ListProveedoresComponent},
  {path: 'clientes/showclientes', component: ListClientesComponent},
  {path: 'clientes/ver/:id', component: VerClienteComponent},
  {path: 'facturas/ver/:id', component: VerFacturaComponent},
  {path: 'facturas/create/:clienteId', component: AddFacturaComponent},
  {path: 'productos/showproductos', component: ListProductosComponent},
  {path: 'productos/ver/:id', component: VerProductoComponent},
  {path: 'productos/addProducto', component: AddProductoComponent},
  {path: 'login', component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
