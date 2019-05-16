import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListCategoriasComponent } from './list-categorias/list-categorias.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from "@angular/common/http";
import { MatTableModule, MatPaginatorModule, MatSortModule, MatIconModule } from '@angular/material';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { AddCategoriaComponent } from './add-categoria/add-categoria.component';
import { FormsModule }   from '@angular/forms';


@NgModule({
  declarations: [ListCategoriasComponent, AddCategoriaComponent],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule, 
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatDialogModule,
    FormsModule
    
  ],
  exports:[
    ListCategoriasComponent,
    RouterModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatDialogModule,
    FormsModule

  ],
  entryComponents: [AddCategoriaComponent], 
  
})
export class CategoriasModule { }
