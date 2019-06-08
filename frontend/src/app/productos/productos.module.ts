import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductosComponent } from './list-productos/list-productos.component';
import { VerProductoComponent } from './ver-producto/ver-producto.component';
import { AddProductoComponent } from './add-producto/add-producto.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";
import { FilterPipe } from '../filtrados/filter.pipe';
import { AppRoutingModule } from '../app-routing.module';
import { SidenavComponent } from './sidenav/sidenav.component';

@NgModule({
  declarations: [ListProductosComponent, VerProductoComponent, AddProductoComponent, FilterPipe, SidenavComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  exports: [
    ListProductosComponent,
    VerProductoComponent,
    AddProductoComponent,
    FormsModule,
    HttpClientModule,
  ],
})
export class ProductosModule { }
