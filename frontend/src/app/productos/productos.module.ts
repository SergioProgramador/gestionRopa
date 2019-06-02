import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductosComponent } from './list-productos/list-productos.component';
import { VerProductoComponent } from './ver-producto/ver-producto.component';
import { AddProductoComponent } from './add-producto/add-producto.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [ListProductosComponent, VerProductoComponent, AddProductoComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    
  ],
  exports: [
    ListProductosComponent,
    VerProductoComponent,
    AddProductoComponent,
    FormsModule,
    HttpClientModule
  ],
})
export class ProductosModule { }
