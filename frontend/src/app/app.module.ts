import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//Modulos
import { BaseModule } from './base/base.module';
import { CategoriasModule } from './categorias/categorias.module';
import { ProveedoresModule } from './proveedores/proveedores.module';
import { ClientesModule } from './clientes/clientes.module';
import { ProductosModule } from './productos/productos.module';
import { FacturasModule } from './facturas/facturas.module';
import { LineasFacturasModule } from './lineas-facturas/lineas-facturas.module';
import { FiltradosModule } from './filtrados/filtrados.module';

@NgModule({
  declarations: [
    AppComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BaseModule,
    CategoriasModule,
    ProveedoresModule,
    ClientesModule,
    ProductosModule,
    FacturasModule,
    LineasFacturasModule,
    FiltradosModule
  ],
  providers: [
   
  ],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
