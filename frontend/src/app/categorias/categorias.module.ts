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
import { EditCategoriaComponent } from './edit-categoria/edit-categoria.component';
import { AppRoutingModule } from '../app-routing.module';
import { SidenavComponent } from './sidenav/sidenav.component';


@NgModule({
  declarations: [ListCategoriasComponent, AddCategoriaComponent, EditCategoriaComponent, SidenavComponent],
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
    FormsModule,
    AppRoutingModule
    
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
  entryComponents: [AddCategoriaComponent, EditCategoriaComponent], 
  
})
export class CategoriasModule { }
