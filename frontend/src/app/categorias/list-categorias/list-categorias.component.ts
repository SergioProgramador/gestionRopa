import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoriasService } from '../categorias.service';
import { Categoria } from '../categoria';
import { Observable } from 'rxjs';
import { DataSource } from '@angular/cdk/table';

@Component({
  selector: 'app-list-categorias',
  templateUrl: './list-categorias.component.html',
  styleUrls: ['./list-categorias.component.scss']
})
export class ListCategoriasComponent implements OnInit {

  errorMessage: string;
  categorias: Categoria[];

  displayedColumns: string[] = ['id', 'nombre'];
  dataSource = new categoriasDataSource(this.service);

  constructor(private router: Router, private service: CategoriasService) { }

  ngOnInit() {
  }

}

export class categoriasDataSource extends DataSource<any>{

  constructor(private service: CategoriasService){
    super();
  }
  
  connect():Observable<Categoria[]>{
    return this.service.getCategorias();
  }

  disconnect(){

  }
  
}
