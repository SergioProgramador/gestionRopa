import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//Componentes
import { BaseModule } from './base/base.module';
import { CategoriasModule } from './categorias/categorias.module';
import { AddCategoriaComponent } from './categorias/list-categorias/list-categorias.component';


@NgModule({
  declarations: [
    AppComponent  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BaseModule,
    CategoriasModule
  ],
  providers: [
   
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddCategoriaComponent],
})
export class AppModule { }
