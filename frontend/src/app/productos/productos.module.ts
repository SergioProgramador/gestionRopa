import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListProductosComponent } from './list-productos/list-productos.component';
import { VerProductoComponent } from './ver-producto/ver-producto.component';

@NgModule({
  declarations: [ListProductosComponent, VerProductoComponent],
  imports: [
    CommonModule
  ],
  exports: [
    ListProductosComponent,
    VerProductoComponent,
  ],
})
export class ProductosModule { }
