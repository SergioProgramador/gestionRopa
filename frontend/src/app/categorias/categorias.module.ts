import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListCategoriasComponent } from './list-categorias/list-categorias.component';
import {RouterModule} from '@angular/router';
import { HttpClientModule } from "@angular/common/http";
import { MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';

@NgModule({
  declarations: [ListCategoriasComponent],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule, 
  ],
  exports:[
    ListCategoriasComponent,
    RouterModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ]
})
export class CategoriasModule { }
