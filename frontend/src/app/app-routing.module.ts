import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListCategoriasComponent } from './categorias/list-categorias/list-categorias.component';

const routes: Routes = [
  {path: 'categorias/showcategorias', component: ListCategoriasComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
